package com.rabbitdemo.invoice_service.listener;

import com.rabbitdemo.invoice_service.config.RabbitMQConfig;
import com.rabbitdemo.invoice_service.entity.PaymentCompleted;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class InvoiceListener {

    @RabbitListener(queues = RabbitMQConfig.QUEUE)
    public void receivePaymentCompleted(PaymentCompleted paymentCompleted) {
        System.out.println("Invoice Service received payment completed: " + paymentCompleted);

        if (paymentCompleted.isSuccess()) {
            // Fatura oluşturma şimdilik sadece konsol çıktısı
            System.out.println("Invoice created for orderId: " + paymentCompleted.getOrderId());
        } else {
            System.out.println("Payment failed for orderId: " + paymentCompleted.getOrderId());
        }
    }
}
