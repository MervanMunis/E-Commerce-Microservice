package com.microservices.ProductService.mapper;

import com.microservices.ProductService.dto.ProductRequestDto;
import com.microservices.ProductService.dto.ProductResponseDto;
import com.microservices.ProductService.entity.Product;
import com.microservices.ProductService.external.response.CompanyWithProductsResponseDto;

import java.io.IOException;
import java.time.Instant;
import java.util.List;

public class ProductMapper {

    // Map Product entity to ProductResponseDto
    public static ProductResponseDto mapToProductResponseDto(Product product){
        return ProductResponseDto.builder()
                .productId(product.getId())
                .productName(product.getProductName())
                .price(product.getPrice())
                .companyId(product.getCompanyId())
                .quantity(product.getQuantity())
                .description(product.getDescription())
                .category(product.getCategory())
                .bytesImage(product.getImage())
                .build();
    }

    // Map ProductRequestDto to Product entity
    public static Product mapToProduct(ProductRequestDto productRequestDto) throws IOException {
        return Product.builder()
                .id(productRequestDto.getProductId())
                .productName(productRequestDto.getProductName())
                .price(productRequestDto.getPrice())
                .companyId(productRequestDto.getCompanyId())
                .quantity(productRequestDto.getQuantity())
                .description(productRequestDto.getDescription())
                .category(productRequestDto.getCategory())
                .image(productRequestDto.getImage().getBytes())
                .build();
    }

    // Map Product entity to ProductResponseDto for CompanyWithProductsResponseDto
    public static CompanyWithProductsResponseDto.ProductResponseDto mapToProductCompanyResponseDto(Product product){
        return CompanyWithProductsResponseDto.ProductResponseDto.builder()
                .productId(product.getId())
                .productName(product.getProductName())
                .price(product.getPrice())
                .companyId(product.getCompanyId())
                .quantity(product.getQuantity())
                .description(product.getDescription())
                .category(product.getCategory())
                .bytesImage(product.getImage())
                .build();
    }

    // Map CompanyWithProductsResponseDto with additional products
    public static CompanyWithProductsResponseDto mapToCompanyWithProductsResponseDto(Long companyId,
                                                                                     CompanyWithProductsResponseDto companyWithProductsResponseDto,
                                                                                     List<CompanyWithProductsResponseDto.ProductResponseDto> productResponseDtos) {
        return CompanyWithProductsResponseDto.builder()
                .companyId(companyId)
                .userName(companyWithProductsResponseDto.getUserName())
                .companyName(companyWithProductsResponseDto.getCompanyName())
                .location(companyWithProductsResponseDto.getLocation())
                .industryType(companyWithProductsResponseDto.getIndustryType())
                .contactPerson(companyWithProductsResponseDto.getContactPerson())
                .contactEmail(companyWithProductsResponseDto.getContactEmail())
                .contactPhone(companyWithProductsResponseDto.getContactPhone())
                .foundedYear(companyWithProductsResponseDto.getFoundedYear())
                .products(productResponseDtos)
                .build();
    }
}
