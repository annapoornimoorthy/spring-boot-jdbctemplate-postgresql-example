package com.bezkoder.spring.jdbc.postgresql;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.List;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.Configuration;
import javax.cache.configuration.MutableConfiguration;
import org.junit.After;
import org.junit.Before;
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

	@Test
	void contextLoads() {
	}

	@Test
	public void useCachedValue() {
		// given
		System.out.println(redisConfiguration.getRedisServer().isActive());
		CacheManager manager = Caching.getCachingProvider().getCacheManager();

		URI test = manager.getURI();
		System.out.println(test);

		Config redissonConfig = new Config();
		redissonConfig.useSingleServer().setAddress("redis://127.0.0.1:6370");
		MutableConfiguration<String, String> jcacheConfig = new MutableConfiguration<>();

		Configuration<String, String> config = RedissonConfiguration.fromConfig(redissonConfig, jcacheConfig);

		manager.createCache("myCache1", config);

		List<Tutorial> tutorialsCacheMiss = tutorialService.getTutorial();
		System.out.println(tutorialsCacheMiss);

		List<Tutorial> tutorials1FromCache = tutorialService.getTutorial();
		System.out.println(tutorials1FromCache);
	}

}
