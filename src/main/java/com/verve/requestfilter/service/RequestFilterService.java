package com.verve.requestfilter.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class RequestFilterService {

    private static final Logger logger = LoggerFactory.getLogger(RequestFilterService.class);
    private final AtomicInteger requestCount = new AtomicInteger(0);
    private final Set<Integer> uniqueRequests = ConcurrentHashMap.newKeySet(); // Thread-safe set of unique IDs


    public RequestFilterService() {
        // Schedule a task to log unique requests every minute
        new java.util.Timer().scheduleAtFixedRate(new java.util.TimerTask() {
            @Override
            public void run() {
                int count = requestCount.getAndSet(0);
                logger.info("Unique requests in the last minute: " + count);
                uniqueRequests.clear(); // Reset for the next minute
            }
        }, 0, 60 * 1000); // Run every minute
    }

    public boolean trackRequest(int id) {
        // Add the ID to the set of unique requests
        if (uniqueRequests.add(id)) {
            requestCount.incrementAndGet(); // Increment the count if the ID is unique
            return true; // ID is unique
        }
        return false; // ID is not unique
    }

}
