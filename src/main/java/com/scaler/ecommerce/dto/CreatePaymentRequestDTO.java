package com.scaler.ecommerce.dto;

import com.scaler.ecommerce.enums.PaymentMethod;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreatePaymentRequestDTO {

    @NotNull(message = "Payment method is required")
    private PaymentMethod method;
}
