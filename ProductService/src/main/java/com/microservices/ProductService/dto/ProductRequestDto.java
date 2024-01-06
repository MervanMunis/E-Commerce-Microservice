package com.microservices.ProductService.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRequestDto {

    private Long productId;
    private String productName;
    private BigDecimal price;
    private Long companyId;
    private Long quantity;
    private String description;       //*
    private String category;          //*
    private byte[] bytesImage;
    private MultipartFile image;
}
