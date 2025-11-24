package com.example.order_inventory_service.service;

import com.example.common.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderInventoryConsumer {

    @Autowired
    private InventoryService inventoryService;

    @KafkaListener(topics = "orders", groupId = "inventory-group")
    public void consume(Order order) {
        System.out.println("📦 Inventory Service received order: " + order.getOrderId());

        // Use the cache-enabled inventory check
        boolean inStock = inventoryService.checkInventory(order);

        if (inStock) {
            System.out.println("✅ Inventory available for product: " + order.getProductId());
        } else {
            System.out.println("❌ Inventory NOT available for product: " + order.getProductId());
        }
    }
}
