package com.microservices.ReviewProductService.service;

import com.microservices.ReviewProductService.dto.ReviewProductRequestDto;
import com.microservices.ReviewProductService.dto.ReviewProductResponseDto;
import com.microservices.ReviewProductService.external.response.ProductWithReviewResponseDto;

import java.util.List;

public interface IReviewProductService {

    ReviewProductResponseDto createReview(ReviewProductRequestDto reviewProductRequestDto);

    ProductWithReviewResponseDto getAllReviewsByProductId(Long productId);

    ReviewProductResponseDto updateProductReview(Long productId,
                                                 ReviewProductRequestDto reviewProductRequestDto,
                                                 Long id);

    void deleteProductReview(Long productId, Long id);
}
