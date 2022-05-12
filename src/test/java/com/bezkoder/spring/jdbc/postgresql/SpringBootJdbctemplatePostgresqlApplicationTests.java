package com.bezkoder.spring.jdbc.postgresql;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.Configuration;
import javax.cache.configuration.FactoryBuilder;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.AccessedExpiryPolicy;
import javax.cache.expiry.Duration;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.redisson.config.Config;
import org.redisson.jcache.configuration.RedissonConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import com.bezkoder.spring.jdbc.postgresql.model.Tutorial;
import com.bezkoder.spring.jdbc.postgresql.service.TutorialService;

import redis.embedded.RedisServer;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = TestRedisConfiguration.class)
class SpringBootJdbctemplatePostgresqlApplicationTests {

	@Autowired
	TutorialService tutorialService;

	@Autowired
	TestRedisConfiguration redisConfiguration;

	private RedisServer redisServer;

	@BeforeEach
	public void redisConfigSettings() {
//		Config redissonConfig = new Config();
//		redissonConfig.useSingleServer().setAddress("redis://127.0.0.1:6370");
//		MutableConfiguration<String, String> jcacheConfig = new MutableConfiguration<>();
//		Configuration<String, String> config = RedissonConfiguration.fromConfig(redissonConfig, jcacheConfig);
//		CacheManager manager = Caching.getCachingProvider().getCacheManager();
//		manager.createCache("myCache1", config);
	}

	@Test
	public void testCachedValueForService() {
		Config redissonConfig = new Config();
		redissonConfig.useSingleServer().setAddress("redis://127.0.0.1:6370");
		MutableConfiguration<String, String> jcacheConfig = new MutableConfiguration<>();
		Configuration<String, String> config = RedissonConfiguration.fromConfig(redissonConfig, jcacheConfig);
		CacheManager manager = Caching.getCachingProvider().getCacheManager();
		manager.createCache("myCache1", config);
		System.out.println(redisConfiguration.getRedisServer().isActive());
		Cache createdCache = Caching.getCachingProvider().getCacheManager().getCache("myCache1");
		System.out.println(createdCache);

		List<Tutorial> tutorialsCacheMiss = tutorialService.getTutorial();
		System.out.println(tutorialsCacheMiss);

		List<Tutorial> tutorials1FromCache = tutorialService.getTutorial();
		System.out.println(tutorials1FromCache);
	}

	@Test
	public void testCacheInRedisServer() {
		Config redissonConfig = new Config();
		redissonConfig.useSingleServer().setAddress("redis://127.0.0.1:6370");

		MutableConfiguration<String, String> jcacheConfig = new MutableConfiguration<>();
		jcacheConfig.setExpiryPolicyFactory(FactoryBuilder.factoryOf(new AccessedExpiryPolicy(new Duration(TimeUnit.SECONDS, 60))));

		Configuration<String, String> config = RedissonConfiguration.fromConfig(redissonConfig, jcacheConfig);
		CacheManager manager = Caching.getCachingProvider().getCacheManager();
		Cache testCache = manager.createCache("testCache", config);
		testCache.put("first", "first-value");

		assertThat(testCache.get("first")).isNotNull();
		assertThat(testCache.get("first")).isEqualTo("first-value");
		try {
			Thread.sleep(60000);
		} catch (InterruptedException e) {

		}
		System.out.println("==============================value after expiry is ");
		System.out.println(testCache.get("first"));
		assertThat(testCache.get("first")).isNull();

		//HAve eviction settins ie minDelay and maxDelay and test that once expired the cache object is removed

	}

}
