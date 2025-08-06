package com.antoniotmdev.app_interview.unit.service;

import com.antoniotmdev.app_interview.dto.PaymentRequestDto;
import com.antoniotmdev.app_interview.entity.PaymentEntity;
import com.antoniotmdev.app_interview.repository.PaymentRepository;
import com.antoniotmdev.app_interview.service.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;


public class PaymentServiceTest {
    @Mock
    private PaymentRepository paymentRepository;
    @InjectMocks
    private PaymentService paymentService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createPayment_ShouldSaveAndReturnPayment(){

        PaymentRequestDto request = new PaymentRequestDto(
                BigDecimal.valueOf(13.50),
                "EUR"
        );

        PaymentEntity savedPayment = PaymentEntity.builder()
                .id(1L)
                .amount(request.amount())
                .currency(request.currency())
                .timestamp(LocalDateTime.now())
                .build();

        when(paymentRepository.save(any(PaymentEntity.class))).thenReturn(savedPayment);

        // When
        PaymentEntity result = paymentService.createPayment(request);

        // Then
        assertEquals(1L, result.getId());
        assertEquals("EUR", result.getCurrency());
        assertEquals(BigDecimal.valueOf(13.50), result.getAmount());
        verify(paymentRepository, times(1)).save(any(PaymentEntity.class));
    }

    @Test
    void getAllPayments_ShouldReturnListOfPayments() {

        PaymentEntity payment = PaymentEntity.builder()
                .id(1L)
                .amount(BigDecimal.valueOf(50.00))
                .currency("USD")
                .timestamp(LocalDateTime.now())
                .build();

        when(paymentRepository.findAll()).thenReturn(Collections.singletonList(payment));

        // When
        List<PaymentEntity> result = paymentService.getAllPayments();

        // Then
        assertEquals(1, result.size());
        assertEquals("USD", result.get(0).getCurrency());
        verify(paymentRepository, times(1)).findAll();
    }
}
