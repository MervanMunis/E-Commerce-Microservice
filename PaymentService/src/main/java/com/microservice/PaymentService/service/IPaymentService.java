package com.microservice.PaymentService.service;

import com.microservice.PaymentService.dto.PaymentRequestDto;
import com.microservice.PaymentService.dto.PaymentResponseDto;

public interface IPaymentService {
    Long doPayment(PaymentRequestDto paymentRequestDto);
    PaymentResponseDto getPaymentDetailsByOrderId(Long orderId);
}
