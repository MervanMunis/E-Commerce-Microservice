package com.microservices.OrderService.service;

import com.microservices.OrderService.dto.OrderProductPaymentDto;
import com.microservices.OrderService.dto.OrderRequestDto;
import com.microservices.OrderService.dto.OrderResponseDto;

public interface IOrderService {
    OrderResponseDto placeOrder(OrderRequestDto orderRequestDto);
    OrderProductPaymentDto getOrderDetails(Long orderId);
}
