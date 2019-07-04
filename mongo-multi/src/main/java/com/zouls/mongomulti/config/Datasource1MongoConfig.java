package com.zouls.mongomulti.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
@EnableMongoRepositories(basePackages = "com.zouls.mongomulti.entity.datasource1", mongoTemplateRef = "datasource1MongoTemplate")
@ConfigurationProperties(prefix = "spring.data.mongodb.datasource1")
public class Datasource1MongoConfig extends AbstractMongoConfiguration {

    @Getter
    @Setter
    private String host, database, username, password;

    @Getter
    @Setter
    private int port;

    @Override
    public @NonNull
    MongoClient mongoClient() {
        ServerAddress serverAddress = new ServerAddress(host, port);
        MongoCredential mongoCredential = MongoCredential.createCredential(username, database, password.toCharArray());
        MongoClientOptions options = MongoClientOptions.builder()
                .connectionsPerHost(100)
                .socketTimeout(30000)
                .connectTimeout(3000)
                .build();
        return new MongoClient(serverAddress, mongoCredential, options);
    }

    @Override
    protected @NonNull
    String getDatabaseName() {
        return database;
    }

    @Override
    @Bean(name = "datasource1MongoTemplate")
    public @NonNull
    MongoTemplate mongoTemplate() throws Exception {
        return super.mongoTemplate();
    }

    @Override
    public @NonNull
    MappingMongoConverter mappingMongoConverter() throws Exception {
        MappingMongoConverter converter = super.mappingMongoConverter();
        converter.setTypeMapper(new DefaultMongoTypeMapper(null));
        return converter;
    }

    @Bean(name = "datasource1MongoTransactionManager")
    MongoTransactionManager transactionManager(MongoDbFactory dbFactory) {
        return new MongoTransactionManager(dbFactory);
    }
}