package com.example.notifier.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
public class ServiceConfig {

    @Value(value = "${events.connection.timeout}")
    private Long eventsConnectionTimeout;

    @Value(value = "${rabbit.hostname}")
    private String rabbitHostname;

    @Value(value = "${rabbit.queue}")
    private String rabbitQueue;

}
