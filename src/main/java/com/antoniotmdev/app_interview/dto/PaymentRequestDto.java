package com.antoniotmdev.app_interview.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Data Transfer Object (DTO) for creating a new payment.
 *
 * <p>This record is used as the request body when a client performs
 * a POST request to create a new payment. It includes validation
 * annotations to ensure the data integrity:</p>
 *
 * <ul>
 *   <li>{@code amount} - Must be positive and cannot be null.</li>
 *   <li>{@code currency} - Must be a 3-letter ISO currency code (e.g., EUR, USD) and cannot be blank.</li>
 * </ul>
 *
 * Example JSON payload:
 * <pre>
 * {
 *   "amount": 100.50,
 *   "currency": "EUR"
 * }
 * </pre>
 */
public record PaymentRequestDto(

        /** Amount of payment. Must be positive and not null. */
        @NotNull(message = "Amount is required")
        @Positive(message = "Amount must be positive")
        BigDecimal amount,

        /** Currency of the payment in 3-letter ISO format (e.g., EUR, USD). */
        @NotBlank(message = "Currency is required")
        @Size(min = 3, max = 3, message = "Currency must be a 3-letter code (e.g., EUR)")
        String currency
) {}