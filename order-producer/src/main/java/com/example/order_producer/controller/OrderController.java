package com.example.order_producer.controller;

import com.example.common.model.Order;
import com.example.order_producer.service.OrderProducerService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderProducerService orderProducerService;

    public OrderController(OrderProducerService orderProducerService) {
        this.orderProducerService = orderProducerService;
    }

    @PostMapping
    public String placeOrder(@RequestBody Order order) {
        orderProducerService.sendOrder(order);
        return "Order sent to Kafka successfully!";
    }
}
