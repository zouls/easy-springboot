package com.zouls.redismulti.config.redis.cluster;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component("datasource1ConfigProperties")
@ConfigurationProperties(prefix = "datasource1.redis.cluster")
public class Datasource1ConfigProperties {
    private List<String> nodes;
    private String password;
}