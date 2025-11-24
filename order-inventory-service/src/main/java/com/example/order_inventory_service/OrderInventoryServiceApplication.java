package com.example.order_inventory_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class OrderInventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderInventoryServiceApplication.class, args);
	}

}
