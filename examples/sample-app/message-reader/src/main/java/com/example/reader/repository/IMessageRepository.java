package com.example.reader.repository;

import com.example.reader.model.Message;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IMessageRepository extends MongoRepository<Message, String> {
}
