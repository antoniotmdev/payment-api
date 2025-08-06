package com.antoniotmdev.app_interview.service;

import com.antoniotmdev.app_interview.dto.PaymentRequestDto;
import com.antoniotmdev.app_interview.entity.PaymentEntity;
import com.antoniotmdev.app_interview.exception.PaymentNotFoundException;
import com.antoniotmdev.app_interview.repository.PaymentRepository;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Service class responsible for handling business logic related to {@link PaymentEntity} entities.
 *
 *  This class acts as an intermediate layer between the controller and the repository.
 *  It isolates business logic from the web layer and ensures that database operations
 *  are properly encapsulated.
 */
@Service
public class PaymentService {


    @Autowired
    private PaymentRepository paymentRepository;

    /**
     * Create a new {@link PaymentEntity} from the provided DTO.
     *
     * @param request DTO containing payments details.
     * @return Persisted {@link PaymentEntity} entity.
     */
    public PaymentEntity createPayment(PaymentRequestDto request){
        // Map DTO fields to a new PaymentEntity entity.
        PaymentEntity payment = PaymentEntity.builder()
                .amount(request.amount())
                .currency(request.currency())
                .timestamp(LocalDateTime.now())
                .build();

        // Save the new payment in the database
        return paymentRepository.save(payment);
    }

    /**
     * Get all payments
     *
     * @return List of all {@link PaymentEntity} entities.
     */
    public List<PaymentEntity> getAllPayments() {
        return paymentRepository.findAll();
    }

    /**
     * Finds a payment by its ID. If the payment does not exist,
     * a {@link PaymentNotFoundException} is thrown.
     *
     * @param id The ID of the payment to find.
     * @return The {@link PaymentEntity} entity if found.
     */
    @Operation(summary = "Get payment by ID")
    @GetMapping("/{id}")
    public PaymentEntity getPaymentById(Long id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new PaymentNotFoundException("Payment with ID " + id + " not found"));
    }

    /**
     * Delete a payment by ID or throw {@link PaymentNotFoundException} if it does not exist.
     *
     * @param id ID of the payment to delete
     */
    public void deletePayment(Long id) {
        PaymentEntity payment = paymentRepository.findById(id)
                .orElseThrow(() -> new PaymentNotFoundException("Payment with ID " + id + " not found"));
        paymentRepository.delete(payment);
    }
}
