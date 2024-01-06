package com.microservices.OrderService.controller;


import com.microservices.OrderService.dto.OrderProductPaymentDto;
import com.microservices.OrderService.dto.OrderRequestDto;
import com.microservices.OrderService.dto.OrderResponseDto;
import com.microservices.OrderService.service.IOrderService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@Log4j2
@RestController
@RequestMapping("/orders")
public class OrderController {

    private final IOrderService orderService;

    // Endpoint for placing an order
    @PostMapping("/placeOrder")
    public ResponseEntity<OrderResponseDto> placeOrder(@RequestBody OrderRequestDto orderRequestDto){
        log.info("Calling Order Service");
        OrderResponseDto orderResponseDto = orderService.placeOrder(orderRequestDto);

        log.info("Order Id: {}", orderResponseDto.getOrderId());
        return new ResponseEntity<>(orderResponseDto, HttpStatus.OK);
    }

    // Endpoint for retrieving order details by order Id
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderProductPaymentDto> getOrderDetails(@PathVariable Long orderId){

        OrderProductPaymentDto orderProductPaymentDto = orderService.getOrderDetails(orderId);

        return new ResponseEntity<>(orderProductPaymentDto, HttpStatus.OK);

    }
}
