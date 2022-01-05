package com.example.publisher.controller;


import com.example.publisher.config.ServiceConfig;
import com.example.publisher.dto.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping(value = "/messages")
public class MessageController {

    private static final Logger LOG = LoggerFactory.getLogger(MessageController.class);

    @Autowired
    private ServiceConfig serviceConfig;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping
    public Message publish(@RequestBody Message newMessage) {
        newMessage.setTimestamp(LocalDateTime.now());
        rabbitTemplate.convertAndSend(serviceConfig.getRabbitQueue(), newMessage.getTimestamp() + "|" + newMessage.getText());
        return newMessage;
    }
}
