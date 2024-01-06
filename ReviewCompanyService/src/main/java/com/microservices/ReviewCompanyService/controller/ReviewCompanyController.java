package com.microservices.ReviewCompanyService.controller;

import com.microservices.ReviewCompanyService.dto.ReviewCompanyRequestDto;
import com.microservices.ReviewCompanyService.dto.ReviewCompanyResponseDto;
import com.microservices.ReviewCompanyService.external.response.CompanyWithReviewResponseDto;
import com.microservices.ReviewCompanyService.service.IReviewCompanyService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("company-reviews")
public class ReviewCompanyController {

    private final IReviewCompanyService reviewCompanyService;

    // Create a new review for a company
    @PostMapping
    public ResponseEntity<ReviewCompanyResponseDto> createReview(@RequestBody ReviewCompanyRequestDto reviewCompanyRequestDto) {
        ReviewCompanyResponseDto review
                = reviewCompanyService.createReview(reviewCompanyRequestDto);
        return new ResponseEntity<>(review, HttpStatus.CREATED);
    }

    // Get all reviews for a specific company
    @GetMapping("/{companyId}")
    public ResponseEntity<CompanyWithReviewResponseDto> getAllReviewsByCompanyId(@PathVariable("companyId") Long companyId) {
        CompanyWithReviewResponseDto reviews = reviewCompanyService.getAllReviewsByCompanyId(companyId);
        return ResponseEntity.ok(reviews);
    }

    // Update an existing review for a company
    @PutMapping("/update/{companyId}/{reviewId}")
    public ResponseEntity<ReviewCompanyResponseDto> updateCompanyReview(
            @PathVariable("companyId") Long companyId,
            @RequestBody ReviewCompanyRequestDto reviewCompanyRequestDto,
            @PathVariable("reviewId") Long reviewId) {

        ReviewCompanyResponseDto review
                = reviewCompanyService.updateCompanyReview(companyId, reviewCompanyRequestDto, reviewId);

        return ResponseEntity.ok(review);
    }

    // Delete a review for a company
    @DeleteMapping("/{companyId}/{reviewId}")
    public ResponseEntity<String> deleteCompanyReview(@PathVariable("companyId") Long companyId,
                                                      @PathVariable("reviewId") Long reviewId) {
        reviewCompanyService.deleteCompanyReview(companyId, reviewId);
        return ResponseEntity.ok("Review Is Deleted.");
    }
}
