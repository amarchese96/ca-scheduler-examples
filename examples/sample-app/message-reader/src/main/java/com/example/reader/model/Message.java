package com.example.reader.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@Getter
@Setter
public class Message {

    @Id
    private String id;

    private String text;

    private LocalDateTime timestamp;

    public Message() {}

    public Message(String text, LocalDateTime timestamp) {
        this.text = text;
        this.timestamp = timestamp;
    }
}
