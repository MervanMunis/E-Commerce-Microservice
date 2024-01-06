package com.microservices.ProductService.external.response;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class CompanyWithProductsResponseDto {
    private Long companyId;
    private String userName;
    private String companyName;
    private String location;
    private String industryType;
    private String contactPerson;
    private String contactEmail;
    private String contactPhone;
    private String foundedYear;
    private List<ProductResponseDto> products;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ProductResponseDto {
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
}
