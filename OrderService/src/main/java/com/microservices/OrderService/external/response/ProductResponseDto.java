package com.microservices.OrderService.external.response;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponseDto {

    private Long productId;
    private String productName;
    private BigDecimal price;
    private Long companyId;
    private Long quantity;
    private String description;       //*
    private String category;          //*
}
