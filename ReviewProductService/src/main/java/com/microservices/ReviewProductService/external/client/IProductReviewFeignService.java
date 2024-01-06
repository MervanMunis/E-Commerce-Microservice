package com.microservices.ReviewProductService.external.client;

import com.microservices.ReviewProductService.external.response.ProductResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("PRODUCT-SERVICE/products")
public interface IProductReviewFeignService {

    @GetMapping("/{productId}")
    public ProductResponseDto getProductById(@PathVariable("productId") Long productId);
}
