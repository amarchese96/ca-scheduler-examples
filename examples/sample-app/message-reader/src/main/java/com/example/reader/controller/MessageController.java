package com.example.reader.controller;

import com.example.reader.model.Message;
import com.example.reader.service.IMessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(value = "/messages")
public class MessageController {

    private static final Logger LOG = LoggerFactory.getLogger(MessageController.class);

    @Autowired
    private IMessageService messageService;

    @GetMapping(value = "/{id}")
    public Message findOne(@PathVariable String id) {
        LOG.info("Received request for message id {}", id);
        return messageService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public Iterable<Message> findAll() {
        return messageService.findAll();
    }

    @DeleteMapping("/{id}")
    public void deleteMessage(@PathVariable("id") String id) {
        messageService.delete(id);
    }
}
