package com.example.reader.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Collection;
import java.util.Collections;

@Configuration
@EnableMongoRepositories(basePackages = "com.example.reader.repository")
public class MongoConfig extends AbstractMongoClientConfiguration {

    @Autowired
    private ServiceConfig config;

    @Override
    protected String getDatabaseName() {
        return config.getMongoDBName();
    }

    @Override
    public MongoClient mongoClient() {
        ConnectionString connectionString = new ConnectionString(
                String.format("mongodb://%s:%s@%s:%s/%s?authSource=%s",
                        config.getMongoUser(),
                        config.getMongoPass(),
                        config.getMongoHost(),
                        config.getMongoPort(),
                        config.getMongoDBName(),
                        config.getMongoAuthDB())
        );
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();

        return MongoClients.create(mongoClientSettings);
    }

    @Override
    public Collection<String> getMappingBasePackages() {
        return Collections.singleton("com.example.reader");
    }
}