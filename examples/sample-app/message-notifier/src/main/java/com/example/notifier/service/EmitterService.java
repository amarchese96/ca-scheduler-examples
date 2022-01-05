package com.example.notifier.service;

import com.example.notifier.config.ServiceConfig;
import com.example.notifier.repository.EmitterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;

@Service
public class EmitterService {

    private static final Logger LOG = LoggerFactory.getLogger(EmitterService.class);

    @Autowired
    private ServiceConfig config;

    @Autowired
    private EmitterRepository emitterRepository;

    public SseEmitter createEmitter(String subscriberId) {
        SseEmitter emitter = new SseEmitter(config.getEventsConnectionTimeout());
        emitter.onCompletion(() -> emitterRepository.remove(subscriberId));
        emitter.onTimeout(() -> emitterRepository.remove(subscriberId));
        emitter.onError(e -> {
            LOG.error("Create SseEmitter exception", e);
            emitterRepository.remove(subscriberId);
        });
        emitterRepository.addOrReplaceEmitter(subscriberId, emitter);
        return emitter;
    }
}
