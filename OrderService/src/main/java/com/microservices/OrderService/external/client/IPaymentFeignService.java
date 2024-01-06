package com.microservices.OrderService.external.client;

import com.microservices.OrderService.external.request.PaymentRequestDto;
import com.microservices.OrderService.external.response.PaymentResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "PAYMENT-SERVICE/payment")
public interface IPaymentFeignService {

    @RequestMapping(method = RequestMethod.POST)
    Long doPayment(@RequestBody PaymentRequestDto paymentRequestDto);

    @RequestMapping(method = RequestMethod.GET, value = "/orders/{id}")
    PaymentResponseDto getPaymentDetailsByOrderId(@PathVariable("id") Long id);
}
