package com.microservices.ProductService.service;

import com.microservices.ProductService.dto.ProductRequestDto;
import com.microservices.ProductService.dto.ProductResponseDto;
import com.microservices.ProductService.external.response.CompanyWithProductsResponseDto;

import java.io.IOException;
import java.util.List;

public interface IProductService {

    ProductResponseDto createProduct(ProductRequestDto productRequestDto) throws IOException;
    ProductResponseDto getProductById(Long productId);
    List<ProductResponseDto> getAllProducts();
    CompanyWithProductsResponseDto getAllProductsWithCompanyId(Long companyId);
    ProductResponseDto updateProduct(Long productId, ProductRequestDto productRequestDto);
    void reduceQuantity(Long productId, Long quantity);
    String  deleteProduct(Long productId);
}
