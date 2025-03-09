package com.verve.requestfilter.controller;

import com.verve.requestfilter.service.GreetingsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/verve")
public class GreetingsController {

    private final GreetingsService service;

    public GreetingsController(GreetingsService service) {
        this.service = service;
    }

    @GetMapping("/accept")
    public Mono<String> acceptRequest(
            @RequestParam int id,
            @RequestParam(required = false) String endpoint) {

        return service.processRequest(id, endpoint);
    }

}
