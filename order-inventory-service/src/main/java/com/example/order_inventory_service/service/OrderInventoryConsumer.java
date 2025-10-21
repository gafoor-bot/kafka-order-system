package com.example.order_inventory_service.service;

import com.example.common.model.Order;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderInventoryConsumer {

    @KafkaListener(topics = "orders", groupId = "inventory-group")
    public void consume(Order order) {
        System.out.println("📦 Inventory Service received order: " + order.getOrderId());
        // TODO: check inventory logic here
    }
}

