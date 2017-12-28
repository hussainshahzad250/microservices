package com.amhi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import com.amhi.app1.Application1;

/**
 * 
 * @author shahzad
 *
 */

@EnableDiscoveryClient
@SpringBootApplication
public class Application2 {
	public static void main(String[] args) {
		System.setProperty("spring.config.name", "client-server2");
		SpringApplication.run(Application1.class, args);
	}
}
