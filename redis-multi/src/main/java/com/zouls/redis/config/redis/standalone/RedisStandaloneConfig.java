package com.zouls.redis.config.redis.standalone;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.clients.jedis.JedisPoolConfig;

import javax.annotation.Resource;
import java.time.Duration;

import static org.springframework.data.redis.connection.jedis.JedisClientConfiguration.DefaultJedisClientConfigurationBuilder;
import static org.springframework.data.redis.connection.jedis.JedisClientConfiguration.builder;

@Configuration
public class RedisStandaloneConfig {

    @Resource(name = "datasource2ConfigProperties")
    private Datasource2ConfigProperties datasource2ConfigProperties;

    @Resource(name = "jedisPoolProperties")
    private JedisPoolProperties jedisPoolProperties;

    @Primary
    @Bean(name = "datasource2StandaloneConnectionFactory")
    public JedisConnectionFactory datasource2StandaloneConnectionFactory() {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration(datasource2ConfigProperties.getHost(), datasource2ConfigProperties.getPort());
        DefaultJedisClientConfigurationBuilder builder = (DefaultJedisClientConfigurationBuilder) builder();
        JedisClientConfiguration smart_tag_standalone = builder
                .poolConfig(poolConfig())
                .and()
                .clientName("datasource2_standalone")
                .readTimeout(Duration.ofSeconds(10))
                .connectTimeout(Duration.ofSeconds(1))
                .usePooling()
                .build();
        return new JedisConnectionFactory(config, smart_tag_standalone);
    }

    @Bean(name = "datasource2StringRedisTemplate")
    public StringRedisTemplate datasource2StringRedisTemplate(@Qualifier("datasource2StandaloneConnectionFactory") RedisConnectionFactory gpsRedisConnectionFactory) {
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.setConnectionFactory(gpsRedisConnectionFactory);
        return stringRedisTemplate;
    }

    private JedisPoolConfig poolConfig() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(jedisPoolProperties.getMaxIdle());
        poolConfig.setMinIdle(jedisPoolProperties.getMinIdle());
        poolConfig.setMaxTotal(jedisPoolProperties.getMaxTotal());
        poolConfig.setMaxWaitMillis(jedisPoolProperties.getMaxWait());
        return poolConfig;
    }
}