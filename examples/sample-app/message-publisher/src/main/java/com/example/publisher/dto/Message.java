package com.example.publisher.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Message {

    private String text;

    private LocalDateTime timestamp;

    public Message() {}

    public Message(String text, LocalDateTime timestamp) {
        this.text = text;
        this.timestamp = timestamp;
    }
}
