package com.bezkoder.spring.jdbc.postgresql.caching;

import java.util.concurrent.TimeUnit;
import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.Configuration;
import javax.cache.configuration.FactoryBuilder;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.AccessedExpiryPolicy;
import javax.cache.expiry.Duration;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.jcache.configuration.RedissonConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Component;

//
//@Component
//@EnableCaching
public class CacheConfigCustom {
    Logger logger = LoggerFactory.getLogger(CacheConfigCustom.class);

    public CacheConfigCustom() {
//        Config redissonConfig = new Config();
//        redissonConfig.useSingleServer().setAddress("redis://127.0.0.1:6379");
//        redissonConfig.setMaxCleanUpDelay(5);
//        redissonConfig.setMinCleanUpDelay(1);
//        redissonConfig.setCleanUpKeysAmount(2);
//        RedissonClient client = Redisson.create(redissonConfig);
//
//
//        MutableConfiguration<String, String> jcacheConfig = new MutableConfiguration<>();
//
//        jcacheConfig.setExpiryPolicyFactory(FactoryBuilder.factoryOf(new AccessedExpiryPolicy(new Duration(TimeUnit.SECONDS, 60))));
//
//
//        //Config configyaml = Config.fromYAML(new File("/home/annapoorni/xld/jcache-learning/reddison/caching-springboot-jcache/src/main/resources/jcache-redisson.yaml"));
//
//        Configuration<String, String> config = RedissonConfiguration.fromConfig(redissonConfig, jcacheConfig);
//        CacheManager manager = Caching.getCachingProvider().getCacheManager();
//
//        logger.info("Default keys -------------------------------");
//        logger.info(client.getKeys().toString());
//        System.out.println(client.getConfig().toString());
//        logger.info("Default cache ------------------------------", manager.getCacheNames());
//
//        logger.info("----------------------------- testMap ", client.getMap("testMap").size());
//        manager.createCache("myCache1", config);
//        Cache testCache = manager.getCache("myCache1");
//        testCache.put("first", "first value");
//        logger.info("Created cache is -------------", manager.getCache("myCache1"));
//
//

    }
}
