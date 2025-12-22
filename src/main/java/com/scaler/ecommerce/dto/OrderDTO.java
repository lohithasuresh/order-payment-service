package com.scaler.ecommerce.dto;

import com.scaler.ecommerce.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private Long id;
    private double amount;
    private String description;
    private LocalDateTime orderDate;
    private OrderStatus status;
    private Long customerId;
}
