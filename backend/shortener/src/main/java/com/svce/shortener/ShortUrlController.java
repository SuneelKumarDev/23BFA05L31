package com.svce.shortener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ShortUrlController {
    @Autowired
    ShortUrlService service;
    @Autowired
    CustomLoggerService logger;

    @PostMapping("/shorturls")
    public ResponseEntity<?> createShortUrl(@RequestBody Map<String, Object> payload) {
        try {
            String url = (String) payload.get("longUrl");
            String customCode = (String) payload.get("customCode");
            Integer validity = payload.get("validity") != null ? (Integer) payload.get("validity") : null;

            if (url == null || url.trim().isEmpty()) {
                logger.log("backend", "error", "controller", "Missing longUrl");
                return ResponseEntity.badRequest().body(Map.of("error", "Missing longUrl"));
            }

            // Basic URL validation
            if (!url.matches("^https?://\\S+$")) {
                logger.log("backend", "error", "controller", "Invalid URL: " + url);
                return ResponseEntity.badRequest().body(Map.of("error", "Invalid URL"));
            }

            ShortUrl shortUrl = service.createUrl(url, customCode, validity);

            Map<String, Object> resp = new HashMap<>();
            resp.put("shortUrl", shortUrl.getShortCode());
            resp.put("expiresAt", shortUrl.getExpiresAt().toString());
            return ResponseEntity.ok(resp);
        } catch (Exception e) {
            logger.log("backend", "error", "controller", "Create failed: " + e.getMessage());
            return ResponseEntity.status(400).body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/{shortCode}")
    public ResponseEntity<?> redirect(@PathVariable String shortCode, HttpServletRequest req) {
        ShortUrl url = service.getUrl(shortCode);
        if (url == null) {
            logger.log("backend", "error", "controller", "Shortcode not found: " + shortCode);
            return ResponseEntity.status(404).body(Map.of("error", "Shortcode not found"));
        }
        if (url.getExpiresAt().isBefore(LocalDateTime.now())) {
            logger.log("backend", "warn", "controller", "Shortcode expired: " + shortCode);
            return ResponseEntity.status(410).body(Map.of("error", "URL expired"));
        }

        String referrer = req.getHeader("referer");
        service.recordClick(url, referrer != null ? referrer : "-", "IN");
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(java.net.URI.create(url.getOriginalUrl()));
        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }

    @GetMapping("/shorturls/{shortCode}")
    public ResponseEntity<?> getStats(@PathVariable String shortCode) {
        ShortUrl url = service.getUrl(shortCode);
        if (url == null) {
            logger.log("backend", "error", "controller", "Shortcode not found: " + shortCode);
            return ResponseEntity.status(404).body(Map.of("error", "Shortcode not found"));
        }
        Map<String, Object> resp = new HashMap<>();
        resp.put("shortUrl", url.getShortCode());
        resp.put("originalUrl", url.getOriginalUrl());
        resp.put("createdAt", url.getCreatedAt().toString());
        resp.put("expiresAt", url.getExpiresAt().toString());
        resp.put("clicks", url.getClickStats().size());
        resp.put("clickDetails", url.getClickStats());
        return ResponseEntity.ok(resp);
    }
}