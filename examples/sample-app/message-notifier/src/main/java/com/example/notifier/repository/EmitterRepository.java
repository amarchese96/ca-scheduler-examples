package com.example.notifier.repository;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Map;
import java.util.Set;

public interface EmitterRepository {

    void addOrReplaceEmitter(String subscriberId, SseEmitter emitter);

    void remove(String subscriberId);

    Set<Map.Entry<String, SseEmitter>> getAll();
}
