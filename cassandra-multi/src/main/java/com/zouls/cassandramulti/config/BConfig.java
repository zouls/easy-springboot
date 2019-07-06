package com.zouls.cassandramulti.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.cassandra.config.CassandraSessionFactoryBean;
import org.springframework.data.cassandra.core.CassandraAdminTemplate;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Configuration
@ConfigurationProperties("spring.data.cassandra.keyspace-gps")
@EnableCassandraRepositories(
        basePackages = "com.zouls.cassandramulti.entity.gps",
        cassandraTemplateRef = "gpsTemplate"
)
class BConfig extends BaseConfig {

    @Override
    @Primary
    @Bean(name = "gpsTemplate")
    public CassandraAdminTemplate cassandraTemplate() {
        return new CassandraAdminTemplate(Objects.requireNonNull(session().getObject()), cassandraConverter());
    }

    @Bean(name = "gpsOperationsTemplate")
    CassandraOperations cassandraOperationsTemplate() {
        return new CassandraTemplate(Objects.requireNonNull(session().getObject()), cassandraConverter());
    }

    @Override
    @Bean(name = "gpsSession")
    public CassandraSessionFactoryBean session() {
        return getCassandraSessionFactoryBean();
    }

}