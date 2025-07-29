package com.svce.shortener;

import jdk.jfr.DataAmount;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ShortUrl {
    private String shortCode;
    private String originalUrl;
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;
    private List<ClickStats> clickStats = new ArrayList<>();

    public ShortUrl(String shortCode, String originalUrl, LocalDateTime createdAt, LocalDateTime expiresAt) {
        this.shortCode = shortCode;
        this.originalUrl = originalUrl;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
    }


    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }

    public void setClickStats(List<ClickStats> clickStats) {
        this.clickStats = clickStats;
    }

    public void addClick(ClickStats click) {
        clickStats.add(click);
    }

    public List<ClickStats> getClickStats() {
        return clickStats;
    }

    public String getShortCode() {
        return shortCode;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }
}