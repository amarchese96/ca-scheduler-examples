package com.example.reader.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
public class ServiceConfig {

    @Value(value = "${mongo.host}")
    private String mongoHost;

    @Value(value = "${mongo.user}")
    private String mongoUser;

    @Value(value = "${mongo.pass}")
    private String mongoPass;

    @Value(value = "${mongo.auth-db}")
    private String mongoAuthDB;

    @Value(value = "${mongo.port}")
    private String mongoPort;

    @Value(value = "${mongo.db-name}")
    private String mongoDBName;

    @Value(value = "${rabbit.hostname}")
    private String rabbitHostname;

    @Value(value = "${rabbit.queue}")
    private String rabbitQueue;

}
