package com.bezkoder.spring.jdbc.postgresql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootJdbctemplatePostgresqlApplication {

    public static void main(String[] args) {
	    Logger logger = LoggerFactory.getLogger(SpringBootJdbctemplatePostgresqlApplication.class);

	    SpringApplication.run(SpringBootJdbctemplatePostgresqlApplication.class, args);

    }

}
