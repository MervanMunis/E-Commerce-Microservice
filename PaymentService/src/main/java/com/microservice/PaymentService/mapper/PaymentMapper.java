package com.microservice.PaymentService.mapper;

import com.microservice.PaymentService.dto.PaymentMode;
import com.microservice.PaymentService.dto.PaymentRequestDto;
import com.microservice.PaymentService.dto.PaymentResponseDto;
import com.microservice.PaymentService.entity.Payment;

import java.time.Instant;

public class PaymentMapper {

    public static PaymentResponseDto mapToPaymentResponseDto(Payment payment) {
        return PaymentResponseDto.builder()
                .paymentId(payment.getId())
                .paymentDate(payment.getPaymentDate())
                .paymentMode(PaymentMode.valueOf(payment.getPaymentMode()))
                .status(payment.getPaymentStatus())
                .orderId(payment.getOrderId())
                .totalPrice(payment.getTotalPrice())
                .build();

    }

    public static Payment mapToPayment(PaymentRequestDto paymentRequestDto) {
        return Payment
                .builder()
                .paymentDate(Instant.now())
                .paymentMode(paymentRequestDto.getPaymentMode().name())
                .orderId(paymentRequestDto.getOrderId())
                .paymentStatus("SUCCESS")
                .referenceNumber(paymentRequestDto.getReferenceNumber())
                .totalPrice(paymentRequestDto.getTotalPrice())
                .build();
    }
}
