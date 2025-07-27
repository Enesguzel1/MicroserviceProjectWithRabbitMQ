package com.rabbitdemo.order_service.producer;

import com.rabbitdemo.order_service.config.RabbitMqConfig;
import com.rabbitdemo.order_service.entity.Order;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderProducer {
    private final RabbitTemplate rabbitTemplate;

    public OrderProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendOrder(Order order) {
        rabbitTemplate.convertAndSend(RabbitMqConfig.EXCHANGE, RabbitMqConfig.ROUTING_KEY, order);
        System.out.println("Order sent to RabbitMQ: " + order);
    }
}
