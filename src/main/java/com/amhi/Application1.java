package com.amhi;

import io.pivotal.microservices.accounts.AccountRepository;
import io.pivotal.microservices.accounts.AccountsConfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

//@EnableDiscoveryClient
//@SpringBootApplication
//public class Application1 {

@EnableAutoConfiguration
@EnableDiscoveryClient
@Import(AccountsConfiguration.class)
public class Application1 {

	@Autowired
	protected AccountRepository accountRepository;

	public static void main(String[] args) {
		System.setProperty("spring.config.name", "client-server1");
		SpringApplication.run(Application1.class, args);
	}
}

/*
 * @RestController class Controller1 {
 * 
 * @Autowired private DiscoveryClient discoveryClient;
 * 
 * @RequestMapping("/client-server1/{applicationName}") public
 * List<ServiceInstance> serviceInstancesByApplicationName(
 * 
 * @PathVariable String applicationName) { return
 * this.discoveryClient.getInstances(applicationName); } }
 */