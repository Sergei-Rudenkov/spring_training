package com.epam.springdata_example.spring_configuration;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.context.annotation.*;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories("com.epam.springdata_example.repository")
public class MongoDBConfig extends AbstractMongoConfiguration {

    @Override
    public String getDatabaseName() {
        return "spring3";
    }

    @Override
    @Bean
    public Mongo mongo() throws Exception {
        return new MongoClient("127.0.0.1");
    }

    @Override
    protected String getMappingBasePackage() {
        return "com.epam.springdata_example.repository";
    }
}
