package com.example.order_payment_service.service;

import com.example.common.model.Order;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderPaymentConsumer {

    @KafkaListener(topics = "orders", groupId = "payment-group", containerFactory = "kafkaListenerContainerFactory")
    public void consume(Order order) {
        System.out.println("💰 Payment Service received order: " + order.getOrderId());

        // Simulate payment processing
        try {
            Thread.sleep(1000); // simulate some processing time
            System.out.println("✅ Payment processed for order: " + order.getOrderId());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("❌ Payment failed for order: " + order.getOrderId());
        }
    }
}

