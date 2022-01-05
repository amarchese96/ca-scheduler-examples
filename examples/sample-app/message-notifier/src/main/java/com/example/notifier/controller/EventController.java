package com.example.notifier.controller;

import com.example.notifier.service.EmitterService;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequestMapping("/events")
public class EventController {

    private static final Logger LOG = LoggerFactory.getLogger(EventController.class);

    @Autowired
    private EmitterService emitterService;

    @GetMapping
    public SseEmitter subscribeToEvents() {
        String subscriberId = RandomStringUtils.randomAlphanumeric(12);
        return emitterService.createEmitter(subscriberId);
    }
}
