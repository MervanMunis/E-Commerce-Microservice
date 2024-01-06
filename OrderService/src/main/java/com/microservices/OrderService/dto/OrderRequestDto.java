package com.microservices.OrderService.dto;

import com.microservices.OrderService.external.request.PaymentMode;
import lombok.*;

import java.time.Instant;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderRequestDto {

    private Long orderId;
    private Long productId;
    private Long quantity;
    private PaymentMode paymentMode;
    private Long totalPrice;

}
