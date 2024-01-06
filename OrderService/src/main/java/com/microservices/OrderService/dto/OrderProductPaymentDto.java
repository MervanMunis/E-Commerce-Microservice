package com.microservices.OrderService.dto;

import com.microservices.OrderService.external.response.PaymentResponseDto;
import com.microservices.OrderService.external.response.ProductResponseDto;
import lombok.*;

import java.time.Instant;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderProductPaymentDto {

    private Long orderId;
    private Long productId;
    private Long quantity;
    private Instant orderDate;
    private String orderStatus;
    private Long totalPrice;
    private ProductResponseDto productDetails;
    private PaymentResponseDto paymentDetails;

}
