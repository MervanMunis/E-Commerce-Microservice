package com.microservices.OrderService.external.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentRequestDto {

    private Long paymentId;
    private Long orderId;
    private Long totalPrice;
    private Long referenceNumber;
    private PaymentMode paymentMode;

}
