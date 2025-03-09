package com.verve.requestfilter.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.verve.requestfilter.kafka.KafkaPublisher;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Service
@EnableScheduling
public class RequestFilterService {

    private final AtomicInteger uniqueCount = new AtomicInteger(0);
    private final RedisService redisService;
    private final KafkaPublisher kafkaPublisher;

    public RequestFilterService(RedisService redisService){
        this.redisService = redisService;
        this.kafkaPublisher = new KafkaPublisher();
    }

    @Scheduled(fixedRate = 60000)
    public void logUniqueRequests() {
        int count = uniqueCount.getAndSet(0); // Atomically get and reset
        kafkaPublisher.publishMessage("Unique request count in the last minute: "+ count);
    }

    public boolean trackRequest(int id) {
        // Add the ID to the set of unique requests
        if (redisService.isNewRequest(id)) {
            uniqueCount.incrementAndGet(); // Increment the count if the ID is unique
            return true; // ID is unique
        }
        return false; // ID is not unique
    }

}
