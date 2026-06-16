package com.project.upimesh.service;

import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class IdempotencyService {

    /* ensure the same payment is processed only once, 
    even if multiple bridge nodes deliver it simultaneously? */

    private final Map<String, Instant> seen = new ConcurrentHashMap<>();
    // created concurrentHashmap because normal hashmap is not thread safe creates
    // race condition.

    @Value("${project.offlinepayment.idempotency-ttl-seconds:86400}")
    private long ttlSeconds;

    // check whether the packet is duplicate or not
    public boolean claim(String packetHash) {
        Instant now = Instant.now();
        Instant prev = seen.putIfAbsent(packetHash, now);
        return prev == null;
    }

    public int size() {
        return seen.size();
    }

    @Scheduled(fixedDelay = 60_000)
    public void cleanUpExpired() {
        Instant cutoff = Instant.now().minusSeconds(ttlSeconds);
        seen.entrySet().removeIf(e -> e.getValue().isBefore(cutoff));
    }

    public void clear() {
        seen.clear();
    }

}
