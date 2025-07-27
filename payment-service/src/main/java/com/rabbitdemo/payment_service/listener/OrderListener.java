package com.rabbitdemo.payment_service.listener;

import com.rabbitdemo.payment_service.config.RabbitMQConfig;
import com.rabbitdemo.payment_service.entity.Order;
import com.rabbitdemo.payment_service.entity.PaymentCompleted;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class OrderListener {

    private final RabbitTemplate rabbitTemplate;

    public OrderListener(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @RabbitListener(queues = RabbitMQConfig.ORDER_QUEUE)
    public void receiveOrder(Order order) {
        System.out.println("Payment Service received order: " + order);

        // Payment işlemi şimdilik konsol çıktısı ile yapılıyor
        System.out.println("Payment processed for orderId: " + order.getOrderId());

        PaymentCompleted paymentCompleted = new PaymentCompleted(order.getOrderId(), true);
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.PAYMENT_EXCHANGE,
                RabbitMQConfig.PAYMENT_COMPLETED_ROUTING_KEY,
                paymentCompleted
        );

        System.out.println("Payment completed event sent for orderId: " + order.getOrderId());
    }
}
