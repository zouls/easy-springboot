package com.zouls.redismulti.config.redis.cluster;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;

@Configuration
public class RedisClusterConfig {

    @Resource(name = "datasource1ConfigProperties")
    private Datasource1ConfigProperties datasource1ConfigProperties;

    @Bean(name = "datasource1ClusterConnectionFactory")
    public RedisConnectionFactory datasource1ClusterConnectionFactory() {
        RedisClusterConfiguration config = new RedisClusterConfiguration(datasource1ConfigProperties.getNodes());
        config.setPassword(RedisPassword.of(datasource1ConfigProperties.getPassword()));
        return new JedisConnectionFactory(config);
    }

    @Bean(name = "datasource1RedisTemplate")
    public StringRedisTemplate redisTemplate(@Qualifier("datasource1ClusterConnectionFactory") RedisConnectionFactory datasource1ClusterConnectionFactory) {
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.setConnectionFactory(datasource1ClusterConnectionFactory);
        return stringRedisTemplate;
    }


}