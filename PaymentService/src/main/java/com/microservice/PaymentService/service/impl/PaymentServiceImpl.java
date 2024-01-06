package com.microservice.PaymentService.service.impl;

import com.microservice.PaymentService.dto.PaymentRequestDto;
import com.microservice.PaymentService.dto.PaymentResponseDto;
import com.microservice.PaymentService.entity.Payment;
import com.microservice.PaymentService.exception.ResourceNotFoundException;
import com.microservice.PaymentService.mapper.PaymentMapper;
import com.microservice.PaymentService.repository.IPaymentRepository;
import com.microservice.PaymentService.service.IPaymentService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
@Log4j2
public class PaymentServiceImpl implements IPaymentService {

    private IPaymentRepository paymentRepository;

    // Process a payment
    @Override
    public Long doPayment(PaymentRequestDto paymentRequestDto) {
        log.info("Recording Payment Details: {}", paymentRequestDto);

        Payment payment = PaymentMapper.mapToPayment(paymentRequestDto);
        Payment savedPayment = paymentRepository.save(payment);

        // Process a payment
        log.info("Payment Completed with Id: {}", savedPayment.getId());
        return PaymentMapper.mapToPaymentResponseDto(savedPayment).getOrderId();
    }

    // Retrieve payment details by orderId
    @Override
    public PaymentResponseDto getPaymentDetailsByOrderId(Long orderId) {
        log.info("Getting payment details for the Order Id: {}", orderId);

        Payment payment = paymentRepository.findByOrderId(orderId);

        return PaymentMapper.mapToPaymentResponseDto(payment);
    }

}
