package com.microservices.ProductService.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "COMPANY_ID")
    private Long companyId;

    @Column(name = "PRODUCT_NAME")
    private String productName;

    @Column(name = "description")       //*
    private String description;

    @Column(name = "PRICE")
    private BigDecimal price;

    @Column(name = "QUANTITY")
    private Long quantity;

    @Column(name = "CATEGORY")         //*
    private String category;

    @Lob
    @Column(columnDefinition = "longblob")
    private byte[] image;
}
