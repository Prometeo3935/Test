package com.ServiceDevice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import org.springframework.web.client.RestTemplate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class ServiceApplication {

	private static final Logger logger = LoggerFactory.getLogger(ServiceApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ServiceApplication.class, args);
	}
	


}