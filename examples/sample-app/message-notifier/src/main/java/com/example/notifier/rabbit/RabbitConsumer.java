package com.example.notifier.rabbit;

import com.example.notifier.dto.EventDto;
import com.example.notifier.service.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class RabbitConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(RabbitConsumer.class);

    @Autowired
    private NotificationService notificationService;

    @RabbitListener(queues = "myqueue")
    public void listen(String message) {
        LOG.info("Received message {} from myqueue", message);

        String[] messageParts = message.split("\\|");

        EventDto event = new EventDto();
        event.setType("MessagePublished");
        event.setBody(new HashMap<>() {{
            put("timestamp", messageParts[0]);
            put("text", messageParts[1]);
        }});

        notificationService.sendNotification(event);
    }
}
