package com.svce.shortener;

import java.time.LocalDateTime;

public class ClickStats {
    private LocalDateTime timestamp;
    private String referrer;
    private String geo;

    public ClickStats(LocalDateTime timestamp, String referrer, String geo) {
        this.timestamp = timestamp;
        this.referrer = referrer;
        this.geo = geo;
    }

    // getters

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    public String getReferrer() {
        return referrer;
    }
    public String getGeo() {
        return geo;
    }
}