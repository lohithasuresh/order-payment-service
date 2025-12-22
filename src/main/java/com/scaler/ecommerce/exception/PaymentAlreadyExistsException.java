package com.scaler.ecommerce.exception;

public class PaymentAlreadyExistsException extends RuntimeException {
    public PaymentAlreadyExistsException(String message) {
        super(message);
    }
}
