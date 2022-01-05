package com.example.notifier.service;

import com.example.notifier.dto.EventDto;
import com.example.notifier.dto.EventMapper;
import com.example.notifier.repository.EmitterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

@Service
public class NotificationService {

    @Autowired
    private EmitterRepository emitterRepository;

    @Autowired
    private EventMapper eventMapper;

    private static final Logger LOG = LoggerFactory.getLogger(NotificationService.class);

    public void sendNotification(EventDto event) {
        if (event == null) {
            LOG.debug("No server event to send to device.");
            return;
        }

        SseEmitter.SseEventBuilder sseEventBuilder = eventMapper.toSseEventBuilder(event);

        emitterRepository.getAll().forEach((entry) -> {
            String subscriberId = entry.getKey();
            SseEmitter sseEmitter = entry.getValue();

            try {
                LOG.debug("Sending event: {} for member: {}", event, subscriberId);
                sseEmitter.send(sseEventBuilder);
            } catch (IOException | IllegalStateException e) {
                LOG.debug("Error while sending event: {} for subscriber: {} - exception: {}", event, subscriberId, e);
                emitterRepository.remove(subscriberId);
            }
        });
    }

}
