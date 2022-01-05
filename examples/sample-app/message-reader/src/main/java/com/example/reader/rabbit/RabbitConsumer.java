package com.example.reader.rabbit;

import com.example.reader.model.Message;
import com.example.reader.service.IMessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class RabbitConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(RabbitConsumer.class);

    private static final String id = "1";

    @Autowired
    private IMessageService messageService;

    @RabbitListener(queues = "myqueue")
    public void listen(String in) {
        LOG.info("Received message {} from myqueue", in);

        String[] messageParts = in.split("\\|");

        Message message = new Message();
        message.setId(id);
        message.setTimestamp(LocalDateTime.parse(messageParts[0]));
        message.setText(messageParts[1]);

        messageService.save(message);
    }
}
