package com.microservices.ReviewProductService.mapper;

import com.microservices.ReviewProductService.dto.ReviewProductRequestDto;
import com.microservices.ReviewProductService.dto.ReviewProductResponseDto;
import com.microservices.ReviewProductService.entity.ReviewProduct;
import com.microservices.ReviewProductService.external.response.ProductResponseDto;
import com.microservices.ReviewProductService.external.response.ProductWithReviewResponseDto;

import java.util.List;

public class ReviewProductMapper {

    // Map ReviewProduct entity to ReviewProductResponseDto
    public static ReviewProductResponseDto mapToReviewResponseDto(ReviewProduct reviewProduct) {
        return ReviewProductResponseDto.builder()
                .id(reviewProduct.getId())
                .productId(reviewProduct.getProductId())
                .productComment(reviewProduct.getProductComment())
                .productReview(reviewProduct.getProductReview())
                .build();
    }

    // Map ReviewProductRequestDto to ReviewProduct entity
    public static ReviewProduct mapToReview(ReviewProductRequestDto reviewProductRequestDto) {
        return ReviewProduct.builder()
                .productId(reviewProductRequestDto.getProductId())
                .productComment(reviewProductRequestDto.getProductComment())
                .productReview(reviewProductRequestDto.getProductReview())
                .build();
    }

    // Map ProductResponseDto and List of ReviewProductResponseDto to ProductWithReviewResponseDto
    public static ProductWithReviewResponseDto mapToProductWithReviewResponseDto(
            ProductResponseDto productResponseDto,
            List<ReviewProductResponseDto> reviewProductResponseDtos) {

        return ProductWithReviewResponseDto.builder()
                .productResponseDto(productResponseDto)
                .reviewProductResponseDtos(reviewProductResponseDtos)
                .build();
    }
}
