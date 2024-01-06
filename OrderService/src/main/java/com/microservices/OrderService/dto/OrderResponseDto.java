package com.microservices.OrderService.dto;

import lombok.*;

import java.time.Instant;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderResponseDto {

    private Long orderId;
    private Long productId;
    private Long quantity;
    private Instant orderDate;
    private String orderStatus;
    private Long totalPrice;
}
