package com.antoniotmdev.app_interview.controller;

import com.antoniotmdev.app_interview.dto.PaymentRequestDto;
import com.antoniotmdev.app_interview.entity.PaymentEntity;
import com.antoniotmdev.app_interview.repository.PaymentRepository;
import com.antoniotmdev.app_interview.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing payments.
 * Provides endpoints to create, retrieve, and delete payments.
 */
@RestController
@RequestMapping("/payments")
@Tag(name = "Payments", description = "Operations related to payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    /**
     * Create a new payment.
     *
     * @param request DTO containing amount and currency
     * @return Created payment with HTTP 201 status.
     */
    @Operation(summary = "Create a new payment")
    @PostMapping
    public ResponseEntity<PaymentEntity> createPayment(@Valid @RequestBody PaymentRequestDto request) {
        PaymentEntity createdPayment = paymentService.createPayment(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPayment);
    }

    /**
     * Get all stored payments.
     *
     * @return List of all payments with HTTP 200 status.
     */
    @Operation(summary = "Get all payments")
    @GetMapping
    public ResponseEntity<List<PaymentEntity>> getAllPayments() {
        List<PaymentEntity> payments = paymentService.getAllPayments();
        return ResponseEntity.ok(payments);
    }

    /**
     * Get a payment by its ID.
     *
     * @param id Payment ID.
     * @return Payment if found, or HTTP 404 if not found.
     */
    @Operation(summary = "Get payment by ID")
    @GetMapping("/{id}")
    public ResponseEntity<PaymentEntity> getPaymentById(@PathVariable Long id) {
        PaymentEntity payment = paymentService.getPaymentById(id);
        return ResponseEntity.ok(payment);
    }

    /**
     * Delete a payment by its ID.
     *
     * @param id Payment ID.
     * @return HTTP 204 if deleted, or HTTP 404 if not found.
     */
    @Operation(summary = "Delete payment by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Payment deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Payment not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable Long id) {
        paymentService.deletePayment(id);
        return ResponseEntity.noContent().build();
    }

}
