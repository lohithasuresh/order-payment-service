package com.scaler.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.scaler.ecommerce.enums.TransactionType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "transactions")
@Schema(hidden = true)
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
    @JsonIgnore
    @Schema(hidden = true)
    private Payment payment;
}
