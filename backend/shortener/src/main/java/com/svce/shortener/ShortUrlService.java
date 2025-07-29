package com.svce.shortener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class ShortUrlService {
    @Autowired
    ShortUrlRepository repo;

    @Autowired
    CustomLoggerService logger;

    private final String CHAR_POOL = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private final int CODE_LENGTH = 6;

    public String generateUniqueShortCode() {
        String code;
        Random rand = new Random();
        do {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < CODE_LENGTH; i++) {
                sb.append(CHAR_POOL.charAt(rand.nextInt(CHAR_POOL.length())));
            }
            code = sb.toString();
        } while (repo.existsByShortCode(code));
        return code;
    }

    public ShortUrl createUrl(String url, String customCode, Integer validityMinutes) {
        String code = customCode != null && !customCode.isEmpty() ? customCode : generateUniqueShortCode();
        if (repo.existsByShortCode(code)) {
            throw new RuntimeException("Short code already exists");
        }

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expiresAt = now.plusMinutes(validityMinutes != null ? validityMinutes : 30);

        ShortUrl shortUrl = new ShortUrl(code, url, now, expiresAt);
        repo.save(shortUrl);

        logger.log("backend", "info", "service", "Short URL created: " + code);

        return shortUrl;
    }

    public ShortUrl getUrl(String code) {
        return repo.findByShortCode(code);
    }

    public void recordClick(ShortUrl shortUrl, String referrer, String geo) {
        shortUrl.addClick(new ClickStats(LocalDateTime.now(), referrer, geo));
        logger.log("backend", "info", "service", "Click recorded for: " + shortUrl.getShortCode());
    }
}