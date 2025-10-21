package com.example.order_notification_service.service;

import com.example.common.model.Order;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderNotificationConsumer {

    @KafkaListener(topics = "orders", groupId = "notification-group", containerFactory = "kafkaListenerContainerFactory")
    public void consume(Order order) {
        System.out.println("📨 Notification Service received order: " + order.getOrderId());

        // Simulate sending a notification (e.g., email, SMS)
        System.out.println("📢 Notification sent for order: " + order.getOrderId() +
                " | Status: " + order.getStatus());
    }
}

