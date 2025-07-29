package com.svce.shortener;

import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class ShortUrlRepository {
    private Map<String, ShortUrl> urlMap = new ConcurrentHashMap<>();

    public ShortUrl save(ShortUrl url) {
        urlMap.put(url.getShortCode(), url);
        return url;
    }

    public ShortUrl findByShortCode(String code) {
        return urlMap.get(code);
    }

    public boolean existsByShortCode(String code) {
        return urlMap.containsKey(code);
    }
}