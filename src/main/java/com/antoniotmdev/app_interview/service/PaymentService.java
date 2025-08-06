package com.antoniotmdev.app_interview.service;

import com.antoniotmdev.app_interview.dto.PaymentRequestDto;
import com.antoniotmdev.app_interview.entity.PaymentEntity;
import com.antoniotmdev.app_interview.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    /**
     * Create a new payment
     * @param request
     * @return
     */
    public PaymentEntity createPayment(PaymentRequestDto request){
        PaymentEntity payment = PaymentEntity.builder()
                .amount(request.amount())
                .currency(request.currency())
                .timestamp(LocalDateTime.now())
                .build();

        return paymentRepository.save(payment);
    }

    /**
     * Get all payments
     */
    public List<PaymentEntity> getAllPayments() {
        return paymentRepository.findAll();
    }
}
