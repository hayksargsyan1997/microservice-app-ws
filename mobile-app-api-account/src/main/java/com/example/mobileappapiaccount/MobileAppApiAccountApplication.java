package com.example.mobileappapiaccount;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MobileAppApiAccountApplication {

	public static void main(String[] args) {
		SpringApplication.run(MobileAppApiAccountApplication.class, args);
	}

}
