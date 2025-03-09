package com.verve.requestfilter.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class GreetingsService {
    private final RequestFilterService requestFilterService;

    public GreetingsService(RequestFilterService requestFilterService) {
        this.requestFilterService = requestFilterService;
    }

    public Mono<String> processRequest(int id, String endpoint) {
        // Track the request using RequestFilterService
        boolean isUnique = requestFilterService.trackRequest(id);

        // Log if the request is unique
        if (isUnique) {
            // Business logic can be added here in the future
        }

        return Mono.just("ok");
    }
}
