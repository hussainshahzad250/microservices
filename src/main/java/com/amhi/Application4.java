package com.amhi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

import com.amhi.configuration.AppConfig;

/**
 * 
 * @author shahzad
 *
 */

// @Configuration
// @ComponentScan("com.amhi.*")
// @EnableAutoConfiguration
@SpringBootApplication(scanBasePackages = { "com.amhi.*" })
@Import(AppConfig.class)
@EnableDiscoveryClient
public class Application4 {

	public static void main(String[] args) {
		System.setProperty("spring.config.name", "client-server4");
		SpringApplication.run(Application4.class, args);
	}
}
