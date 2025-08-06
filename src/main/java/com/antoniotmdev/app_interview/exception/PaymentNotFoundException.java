package com.antoniotmdev.app_interview.exception;

/**
 * Custom exception thrown when a payment with the given ID is not found.
 */
public class PaymentNotFoundException extends RuntimeException{
    public PaymentNotFoundException(String message) {
        super(message);
    }
}
