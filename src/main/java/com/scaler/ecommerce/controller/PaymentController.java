package com.scaler.ecommerce.controller;

import com.scaler.ecommerce.entity.Payment;
import com.scaler.ecommerce.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/{orderId}")
    public Payment makePayment(
            @PathVariable Long orderId,
            @RequestBody Payment payment
    ) {
        return paymentService.makePayment(orderId, payment);
    }

    @GetMapping
    public List<Payment> getAllPayments() {
        return paymentService.getAllPayments();
    }
}
