package com.bezkoder.spring.jdbc.postgresql;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.springframework.boot.test.context.TestConfiguration;

import redis.embedded.RedisServer;

@TestConfiguration
public class TestRedisConfiguration {

    private RedisServer redisServer;

    public TestRedisConfiguration(RedisProperties redisProperties) {
        this.redisServer = new RedisServer(redisProperties.getRedisPort());
    }

    @PostConstruct
    public void postConstruct() {
        redisServer.start();
    }

    @PreDestroy
    public void preDestroy() {
        redisServer.stop();
    }

    public RedisServer getRedisServer() {
        return redisServer;
    }

    public void setRedisServer(final RedisServer redisServer) {
        this.redisServer = redisServer;
    }
}