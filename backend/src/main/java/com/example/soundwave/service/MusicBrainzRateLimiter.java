package com.example.soundwave.service;

import org.springframework.stereotype.Component;

/**
 * MusicBrainz asks clients to limit to about one request per second.
 */
@Component
public class MusicBrainzRateLimiter {

    private final Object lock = new Object();
    private long lastRequestAtMs;

    public void acquire() {
        synchronized (lock) {
            long now = System.currentTimeMillis();
            long waitMs = 1100 - (now - lastRequestAtMs);
            if (waitMs > 0) {
                try {
                    Thread.sleep(waitMs);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            lastRequestAtMs = System.currentTimeMillis();
        }
    }
}
