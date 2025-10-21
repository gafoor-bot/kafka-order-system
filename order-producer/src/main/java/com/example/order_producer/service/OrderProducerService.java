package com.example.order_producer.service;

import com.example.common.model.Order;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderProducerService {

    private static final String TOPIC = "orders";
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public OrderProducerService(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendOrder(Order order) {
        kafkaTemplate.send(TOPIC, order.getOrderId(), order);
        System.out.println("✅ Order sent to Kafka: " + order.getOrderId());
    }
}

