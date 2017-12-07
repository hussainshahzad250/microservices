package com.amhi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
/**
 * 
 * @author shahzad
 *
 */
@EnableAutoConfiguration
@EnableDiscoveryClient

public class Application3 {

	
	
	public static void main(String[] args) {
		System.setProperty("spring.config.name", "client-server3");
		SpringApplication.run(Application3.class, args);
	}
}
