package com.microservices.OrderService.mapper;

import com.microservices.OrderService.dto.OrderProductPaymentDto;
import com.microservices.OrderService.dto.OrderRequestDto;
import com.microservices.OrderService.dto.OrderResponseDto;
import com.microservices.OrderService.entity.Order;
import com.microservices.OrderService.external.request.PaymentRequestDto;
import com.microservices.OrderService.external.response.PaymentResponseDto;
import com.microservices.OrderService.external.response.ProductResponseDto;

import java.time.Instant;

public class OrderMapper {

    // Mapping Order entity to OrderResponseDto
    public static OrderResponseDto mapToOrderResponseDto(Order order){
        return OrderResponseDto.builder()
                .orderId(order.getId())
                .orderStatus(order.getOrderStatus())
                .orderDate(order.getOrderDate())
                .quantity(order.getQuantity())
                .totalPrice(order.getTotalPrice())
                .productId(order.getProductId())
                .build();
    }

    // Mapping OrderRequestDto to Order entity
    public static Order mapToOrder(OrderRequestDto orderRequestDto){
        return Order.builder()
                .totalPrice(orderRequestDto.getTotalPrice())
                .orderStatus("CREATED")
                .productId(orderRequestDto.getProductId())
                .orderDate(Instant.now())
                .quantity(orderRequestDto.getQuantity())
                .build();
    }

    // Mapping OrderRequestDto to Order entity
    public static PaymentRequestDto mapToPaymentRequestDto(OrderRequestDto orderRequestDto, Order order){
        return PaymentRequestDto.builder()
                .orderId(order.getId())
                .totalPrice(orderRequestDto.getTotalPrice())
                .paymentMode(orderRequestDto.getPaymentMode())
                .build();
    }

    // Mapping Order, PaymentResponseDto, and ProductResponseDto to OrderProductPaymentDto
    public static OrderProductPaymentDto mapToOrderProductPaymentResponseDto(Order order,
                                                                 PaymentResponseDto paymentResponseDto,
                                                                 ProductResponseDto productResponseDto) {
        return OrderProductPaymentDto.builder()
                .orderId(order.getId())
                .productId(order.getProductId())
                .quantity(order.getQuantity())
                .orderStatus(order.getOrderStatus())
                .totalPrice(order.getTotalPrice())
                .orderDate(order.getOrderDate())
                .productDetails(productResponseDto)
                .paymentDetails(paymentResponseDto)
                .build();
    }
}
