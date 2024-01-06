package com.microservices.ReviewProductService.controller;

import com.microservices.ReviewProductService.dto.ReviewProductRequestDto;
import com.microservices.ReviewProductService.dto.ReviewProductResponseDto;
import com.microservices.ReviewProductService.external.response.ProductWithReviewResponseDto;
import com.microservices.ReviewProductService.service.IReviewProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("product-reviews")
public class ReviewProductController {

    private final IReviewProductService reviewProductService;

    // Endpoint to create a new product review
    @PostMapping
    public ResponseEntity<ReviewProductResponseDto> createReview(@RequestBody ReviewProductRequestDto reviewProductRequestDto) {
        ReviewProductResponseDto review = reviewProductService.createReview(reviewProductRequestDto);
        return new ResponseEntity<>(review, HttpStatus.CREATED);
    }

    // Endpoint to get all reviews for a specific product
    @GetMapping("/{productId}")
    public ResponseEntity<ProductWithReviewResponseDto> getAllReviewsByProductId(@PathVariable("productId") Long productId) {
        ProductWithReviewResponseDto reviews = reviewProductService.getAllReviewsByProductId(productId);
        return ResponseEntity.ok(reviews);
    }

    // Endpoint to update an existing product review
    @PutMapping("/update/{productId}/{reviewId}")
    public ResponseEntity<ReviewProductResponseDto> updateProductReview(@PathVariable("productId") Long productId,
                                                                        @RequestBody ReviewProductRequestDto reviewProductRequestDto,
                                                                        @PathVariable("reviewId") Long reviewId){
        ReviewProductResponseDto review = reviewProductService.updateProductReview(productId, reviewProductRequestDto, reviewId);
        return ResponseEntity.ok(review);
    }

    // Endpoint to delete a product review
    @DeleteMapping("/{productId}/{reviewId}")
    public ResponseEntity<String> deleteProductReview(@PathVariable("productId") Long productId,
                                                      @PathVariable("reviewId") Long reviewId) {
        reviewProductService.deleteProductReview(productId, reviewId);
        return ResponseEntity.ok("Review Is Deleted.");
    }



}
