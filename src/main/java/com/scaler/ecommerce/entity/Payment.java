package com.scaler.ecommerce.entity;

import com.scaler.ecommerce.enums.PaymentMethod;
import com.scaler.ecommerce.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;

    private LocalDateTime paymentDate = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    @Enumerated(EnumType.STRING)
    private PaymentMethod method;

    @OneToOne
    @JoinColumn(name = "order_id", unique = true)
    private Order order;
}
