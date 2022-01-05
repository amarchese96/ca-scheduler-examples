package com.example.notifier.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryEmitterRepository implements EmitterRepository {

    private static final Logger LOG = LoggerFactory.getLogger(InMemoryEmitterRepository.class);

    private final Map<String, SseEmitter> userEmitterMap = new ConcurrentHashMap<>();

    @Override
    public void addOrReplaceEmitter(String subscriberId, SseEmitter emitter) {
        userEmitterMap.put(subscriberId, emitter);
    }

    @Override
    public void remove(String subscriberId) {
        if (userEmitterMap.containsKey(subscriberId)) {
            LOG.debug("Removing emitter for member: {}", subscriberId);
            userEmitterMap.remove(subscriberId);
        } else {
            LOG.debug("No emitter to remove for member: {}", subscriberId);
        }
    }

    @Override
    public Set<Map.Entry<String, SseEmitter>> getAll() {
        return new HashSet<>(userEmitterMap.entrySet());
    }
}
