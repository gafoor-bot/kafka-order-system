package com.example.order_producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.example.common.model")
@EnableJpaRepositories(basePackages = "com.example.common.repository")
public class OrderProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderProducerApplication.class, args);
	}

}
