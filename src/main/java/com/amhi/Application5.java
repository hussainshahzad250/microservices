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
@SpringBootApplication(scanBasePackages = { "com.amhi.app4" })
@Import(AppConfig.class)
@EnableDiscoveryClient
public class Application5 {

	public static void main(String[] args) {
		System.setProperty("spring.config.name", "client-server5");
		SpringApplication.run(Application5.class, args);
	}
}
