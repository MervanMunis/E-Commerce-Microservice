package com.microservices.OrderService.service.impl;

import com.microservices.OrderService.dto.OrderProductPaymentDto;
import com.microservices.OrderService.dto.OrderRequestDto;
import com.microservices.OrderService.dto.OrderResponseDto;
import com.microservices.OrderService.entity.Order;
import com.microservices.OrderService.exception.ResourceNotFoundException;
import com.microservices.OrderService.external.client.IPaymentFeignService;
import com.microservices.OrderService.external.client.IProductFeignService;
import com.microservices.OrderService.external.request.PaymentRequestDto;
import com.microservices.OrderService.external.response.PaymentResponseDto;
import com.microservices.OrderService.external.response.ProductResponseDto;
import com.microservices.OrderService.mapper.OrderMapper;
import com.microservices.OrderService.repository.IOrderRepository;
import com.microservices.OrderService.service.IOrderService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@AllArgsConstructor
@Service
public class OrderServiceImpl implements IOrderService {

    private final IOrderRepository orderRepository;

    private IProductFeignService productFeignService;

    private IPaymentFeignService paymentFeignService;


    // Placing an order
    @Override
    public OrderResponseDto placeOrder(OrderRequestDto orderRequestDto) {
        log.info("Placing Order Request: {}", orderRequestDto);

        // Reduce the product quantity using the Product Feign Service
        productFeignService.reduceQuantity(orderRequestDto.getProductId(), orderRequestDto.getQuantity());

        log.info("Creating Order With Status CREATED");

        Order order = OrderMapper.mapToOrder(orderRequestDto);

        Order savedOrder = orderRepository.save(order);

        // Mapping OrderRequestDto and savedOrder to PaymentRequestDto
        log.info("Creating Payment Request...");
        PaymentRequestDto paymentRequestDto = OrderMapper.mapToPaymentRequestDto(orderRequestDto, savedOrder);

        log.info("Calling Payment Request With Payment Request: {}", paymentRequestDto);

        String orderStatus;
        try {
            // Call the Payment Feign Service to process the payment
            paymentFeignService.doPayment(paymentRequestDto);
            log.info("Payment Done Successfully. Changing The Oder Status To PLACED");
            orderStatus = "PLACED";
        } catch (Exception e) {
            log.error("Error Occurred In Payment. Changing Order Status To PAYMENT_FAILED");
            orderStatus = "PAYMENT_FAILED";
        }

        order.setOrderStatus(orderStatus);
        orderRepository.save(order);

        log.info("Placing Order Request: {}", orderRequestDto);
        return OrderMapper.mapToOrderResponseDto(order);
    }

    // Get details of an order including product and payment information
    @Override
    public OrderProductPaymentDto getOrderDetails(Long orderId) {
        log.info("Get order details for Order Id : {}", orderId);

        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new ResourceNotFoundException("Order not found for the order Id:" + orderId,
                        "NOT_FOUND",
                        404)
        );
        log.info("Invoking Payment Feign Service To Fetch The Payment For orderId: {}", order.getId());
        PaymentResponseDto paymentResponseDto = paymentFeignService.getPaymentDetailsByOrderId(order.getId());

        log.info("Invoking Product Feign Service To Fetch The Product For productId: {}", order.getProductId());
        ProductResponseDto productResponseDto = productFeignService.getProductById(order.getProductId());

        productResponseDto.setProductId(order.getProductId());
        log.info("Order Details Are Prepared.");
        return OrderMapper.mapToOrderProductPaymentResponseDto(order, paymentResponseDto, productResponseDto);
    }
}
