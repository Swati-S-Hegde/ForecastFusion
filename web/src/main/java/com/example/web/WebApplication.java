package com.example.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
// Specify the package where your JPA entities are located
@EntityScan(basePackages = "com.example.web.DataRepository")
// Specify the package where your JPA repositories are located
@EnableJpaRepositories(basePackages = "com.example.web.DataRepository")

public class WebApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebApplication.class, args);
	}

}
