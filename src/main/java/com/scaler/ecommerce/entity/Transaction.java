package com.scaler.ecommerce.entity;

import com.scaler.ecommerce.enums.TransactionType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;

    @Enumerated(EnumType.STRING)
    private TransactionType type;

    private LocalDateTime transactionDate;

    @ManyToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;
}
