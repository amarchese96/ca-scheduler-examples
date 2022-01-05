package com.example.reader.service;

import com.example.reader.model.Message;
import com.example.reader.repository.IMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MessageServiceImpl implements IMessageService{

    @Autowired
    private IMessageRepository messageRepository;

    @Override
    public Optional<Message> findById(String id) {
        return messageRepository.findById(id);
    }

    @Override
    public Message save(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public Iterable<Message> findAll() {
        return messageRepository.findAll();
    }

    @Override
    public void delete(String id) {
        messageRepository.deleteById(id);
    }

}
