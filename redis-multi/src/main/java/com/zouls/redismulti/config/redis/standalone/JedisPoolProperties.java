package com.zouls.redismulti.config.redis.standalone;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component("jedisPoolProperties")
@ConfigurationProperties(prefix = "datasource1.redis.jedis.pool")
public class JedisPoolProperties {
    private int maxIdle;
    private int minIdle;
    private int maxTotal;
    private long maxWait;
}