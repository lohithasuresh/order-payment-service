package com.scaler.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.scaler.ecommerce.enums.PaymentMethod;
import com.scaler.ecommerce.enums.PaymentStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "payments")
@Schema(hidden = true)
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double amount;

    private LocalDateTime paymentDate = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    @Enumerated(EnumType.STRING)
    private PaymentMethod method;

    @OneToOne
    @JoinColumn(name = "order_id", unique = true, nullable = false)
    @JsonIgnore
    @Schema(hidden = true)
    private Order order;
}
