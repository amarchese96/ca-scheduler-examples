package com.example.reader.service;

import com.example.reader.model.Message;

import java.util.Optional;

public interface IMessageService {

    Optional<Message> findById(String id);

    Message save(Message message);

    Iterable<Message> findAll();

    void delete(String id);
}
