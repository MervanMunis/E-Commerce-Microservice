package com.microservices.OrderService.external.response;

import com.microservices.OrderService.external.request.PaymentMode;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentResponseDto {

    private Long paymentId;
    private String status;
    private PaymentMode paymentMode;
    private Long totalPrice;
    private Instant paymentDate;
    private Long orderId;

}
