package com.scaler.ecommerce.controller;

import com.scaler.ecommerce.dto.PaymentDTO;
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
    public PaymentDTO makePayment(
            @PathVariable Long orderId,
            @RequestBody PaymentDTO paymentRequest
    ) {
        return paymentService.makePayment(orderId, paymentRequest);
    }

    @GetMapping
    public List<PaymentDTO> getAllPayments() {
        return paymentService.getAllPayments();
    }
}
