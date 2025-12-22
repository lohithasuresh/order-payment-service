package com.scaler.ecommerce.service;

import com.scaler.ecommerce.entity.Order;
import com.scaler.ecommerce.entity.Payment;

import java.util.List;

public interface PaymentService {
    Payment makePayment(Long orderId, Payment payment);
    List<Payment> getAllPayments();
}
