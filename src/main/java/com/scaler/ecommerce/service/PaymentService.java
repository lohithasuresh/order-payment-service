package com.scaler.ecommerce.service;

import com.scaler.ecommerce.dto.PaymentDTO;

import java.util.List;

public interface PaymentService {
    PaymentDTO makePayment(Long orderId, PaymentDTO paymentRequest);
    List<PaymentDTO> getAllPayments();
}
