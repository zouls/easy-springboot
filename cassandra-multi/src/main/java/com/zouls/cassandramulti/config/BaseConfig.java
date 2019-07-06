package com.zouls.cassandramulti.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.CassandraSessionFactoryBean;

import java.util.Objects;

public abstract class BaseConfig extends AbstractCassandraConfiguration {
    @Getter @Setter
    protected String contactPoints;
    @Setter
    private String keyspaceName;
    @Getter @Setter
    protected String compression;

    @Override
    protected String getKeyspaceName() {
        return keyspaceName;
    }

    CassandraSessionFactoryBean getCassandraSessionFactoryBean() {
        CassandraSessionFactoryBean session = new CassandraSessionFactoryBean();
        session.setCluster(Objects.requireNonNull(cluster().getObject()));
        session.setConverter(cassandraConverter());
        session.setKeyspaceName(getKeyspaceName());
        session.setSchemaAction(getSchemaAction());
        session.setStartupScripts(getStartupScripts());
        session.setShutdownScripts(getShutdownScripts());
        return session;
    }
}