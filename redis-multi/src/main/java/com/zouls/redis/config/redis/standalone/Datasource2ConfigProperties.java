package com.zouls.redis.config.redis.standalone;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Data
@Component("datasource2ConfigProperties")
@ConfigurationProperties(prefix = "datasource2.redis")
public class Datasource2ConfigProperties {

    private String host;
    private int port;
    private Duration timeout;

}