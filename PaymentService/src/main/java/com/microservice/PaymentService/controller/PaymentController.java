package com.microservice.PaymentService.controller;

import com.microservice.PaymentService.dto.PaymentRequestDto;
import com.microservice.PaymentService.dto.PaymentResponseDto;
import com.microservice.PaymentService.service.IPaymentService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@AllArgsConstructor
@RestController
@RequestMapping("/payment")
public class PaymentController {

    private IPaymentService paymentService;

    // Endpoint for processing a payment
    @PostMapping
    public ResponseEntity<Long> doPayment(@RequestBody PaymentRequestDto paymentRequestDto) {

        // Call the payment service to process the payment
        Long payment = paymentService.doPayment(paymentRequestDto);
        return new ResponseEntity<>(payment, HttpStatus.OK);
    }

    // Endpoint for retrieving payment details by orderId
    @GetMapping("/orders/{orderId}")
    public ResponseEntity<PaymentResponseDto> getPaymentDetailsByOrderId(@PathVariable("orderId") Long orderId){
        log.info("Getting Payment Details With orderId: {}", orderId);
        PaymentResponseDto paymentResponseDto = paymentService.getPaymentDetailsByOrderId(orderId);
    return new ResponseEntity<>(paymentResponseDto, HttpStatus.OK);
    }
}
