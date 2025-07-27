package com.rabbitdemo.order_service.controller;

import com.rabbitdemo.order_service.entity.Order;
import com.rabbitdemo.order_service.producer.OrderProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderProducer producer;

    public OrderController(OrderProducer producer) {
        this.producer = producer;
    }

    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody Order order) {
        producer.sendOrder(order);
        return ResponseEntity.ok("Order created and sent to queue.");
    }
}
