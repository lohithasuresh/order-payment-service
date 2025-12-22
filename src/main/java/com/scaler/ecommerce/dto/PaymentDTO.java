package com.scaler.ecommerce.dto;

import com.scaler.ecommerce.enums.PaymentMethod;
import com.scaler.ecommerce.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {
    private Long id;
    private Double amount;
    private LocalDateTime paymentDate;
    private PaymentStatus status;
    private PaymentMethod method;
    private Long orderId;
}
