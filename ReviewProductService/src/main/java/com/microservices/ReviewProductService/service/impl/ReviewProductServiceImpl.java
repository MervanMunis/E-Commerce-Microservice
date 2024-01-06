package com.microservices.ReviewProductService.service.impl;

import com.microservices.ReviewProductService.dto.ReviewProductRequestDto;
import com.microservices.ReviewProductService.dto.ReviewProductResponseDto;
import com.microservices.ReviewProductService.entity.ReviewProduct;
import com.microservices.ReviewProductService.exception.ResourceNotFoundException;
import com.microservices.ReviewProductService.external.client.IProductReviewFeignService;
import com.microservices.ReviewProductService.external.response.ProductResponseDto;
import com.microservices.ReviewProductService.external.response.ProductWithReviewResponseDto;
import com.microservices.ReviewProductService.mapper.ReviewProductMapper;
import com.microservices.ReviewProductService.repository.IReviewProductRepository;
import com.microservices.ReviewProductService.service.IReviewProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@Service
public class ReviewProductServiceImpl implements IReviewProductService {

    private final IReviewProductRepository reviewRepository;

    private final IProductReviewFeignService productReviewFeignService;

    // Create a new review for a product
    @Override
    public ReviewProductResponseDto createReview(ReviewProductRequestDto reviewProductRequestDto) {
        log.info("Creating The ReviewProduct...");

        ReviewProduct reviewProduct = ReviewProductMapper.mapToReview(reviewProductRequestDto);
        ReviewProduct savedReviewProduct = reviewRepository.save(reviewProduct);

        log.info("ReviewProduct Is Created With productId: {}", reviewProduct.getProductId());

        // Map the saved entity back to DTO for response
        return ReviewProductMapper.mapToReviewResponseDto(savedReviewProduct);
    }

    // Get all reviews for a specified product
    @Override
    public ProductWithReviewResponseDto getAllReviewsByProductId(Long productId) {
        log.info("Getting All Reviews For Specified productId: {}", productId);

        // Retrieve all reviews from the repository based on the productId
        List<ReviewProduct> reviewProducts = reviewRepository.findAllByProductId(productId);

        // Call the Feign client to get product details
        ProductResponseDto productResponseDto = productReviewFeignService.getProductById(productId);

        // Map the review entities to DTOs
        List<ReviewProductResponseDto> reviewProductResponseDtos = reviewProducts.stream()
                .map(ReviewProductMapper::mapToReviewResponseDto)
                .toList();

        // Map product details and reviews to a combined response DTO
        ProductWithReviewResponseDto productWithReviewResponseDto = ReviewProductMapper
                .mapToProductWithReviewResponseDto(productResponseDto, reviewProductResponseDtos);

        log.info("All Reviews Are Listed.");

        return productWithReviewResponseDto;
    }

    // Update a review for a specified product
    @Override
    public ReviewProductResponseDto updateProductReview(Long productId,
                                                        ReviewProductRequestDto reviewProductRequestDto,
                                                        Long reviewId) {
        log.info("Updating ReviewProduct For Specified productId: {}", productId);

        // Retrieve all reviews for the productId
        List<ReviewProduct> reviewProducts = reviewRepository.findReviewByProductId(productId);

        // Find the specific review to update
        ReviewProduct foundedReviewProduct = reviewProducts.stream()
                .filter(reviewProduct -> reviewProduct.getId().equals(reviewId)).findFirst()
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Review Product not found with id: " + reviewId,
                        "Review PRODUCT_NOT_FOUND",
                        404));

        // Update review details
        foundedReviewProduct.setProductComment(reviewProductRequestDto.getProductComment());
        foundedReviewProduct.setProductReview(reviewProductRequestDto.getProductReview());

        ReviewProduct savedReviewProduct = reviewRepository.save(foundedReviewProduct);

        log.info("ReviewProduct Is Updated.");
        return ReviewProductMapper.mapToReviewResponseDto(savedReviewProduct);
    }

    // Update review details
    @Override
    public void deleteProductReview(Long productId, Long reviewId) {
        log.info("ReviewProduct Is Getting Deleted For The Specified productId: {}", productId);

        // Update review details
        List<ReviewProduct> reviewProducts = reviewRepository.findReviewByProductId(productId);

        // Find the specific review to delete
        ReviewProduct foundedReviewProduct = reviewProducts.stream()
                .filter(reviewProduct -> reviewProduct.getId().equals(reviewId)).findFirst().orElseThrow(
                        () -> new ResourceNotFoundException(
                                "Review Product not found with id: " + reviewId,
                                "Review PRODUCT_NOT_FOUND",
                                404));

        // Delete the review from the repository
        reviewRepository.delete(foundedReviewProduct);

        log.info("ReviewProduct Is Deleted");
    }
}
