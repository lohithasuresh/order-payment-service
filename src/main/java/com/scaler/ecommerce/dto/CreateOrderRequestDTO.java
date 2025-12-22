package com.scaler.ecommerce.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class CreateOrderRequestDTO {

    @NotNull(message = "Order amount is required")
    @Positive(message = "Order amount must be greater than zero")
    private Double amount;

    @NotBlank(message = "Order description is required")
    private String description;
}
