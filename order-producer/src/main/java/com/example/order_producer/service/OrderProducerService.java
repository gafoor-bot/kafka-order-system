package com.example.order_producer.service;

import com.example.common.model.Order;
import com.example.common.repository.OrderRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderProducerService {

    private static final String TOPIC = "orders";
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final OrderRepository orderRepository;

    public OrderProducerService(KafkaTemplate<String, Object> kafkaTemplate, 
                                OrderRepository orderRepository) {
        this.kafkaTemplate = kafkaTemplate;
        this.orderRepository = orderRepository;
    }

    @Transactional
    public void sendOrder(Order order) {
        // Step 1: Save order to MySQL database
        orderRepository.save(order);
        System.out.println("💾 Order saved to MySQL: " + order.getOrderId());
        
        // Step 2: Send order to Kafka
        kafkaTemplate.send(TOPIC, order.getOrderId(), order);
        System.out.println("✅ Order sent to Kafka: " + order.getOrderId());
    }
}

