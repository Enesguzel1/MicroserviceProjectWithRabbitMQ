package com.rabbitdemo.payment_service.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentCompleted {
    private String orderId;
    private boolean success;
}
