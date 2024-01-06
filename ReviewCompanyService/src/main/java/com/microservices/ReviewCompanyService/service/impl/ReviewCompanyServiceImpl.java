package com.microservices.ReviewCompanyService.service.impl;

import com.microservices.ReviewCompanyService.dto.ReviewCompanyRequestDto;
import com.microservices.ReviewCompanyService.dto.ReviewCompanyResponseDto;
import com.microservices.ReviewCompanyService.entity.ReviewCompany;
import com.microservices.ReviewCompanyService.exception.ResourceNotFoundException;
import com.microservices.ReviewCompanyService.external.client.ICompanyReviewFeignService;
import com.microservices.ReviewCompanyService.external.response.CompanyResponseDto;
import com.microservices.ReviewCompanyService.external.response.CompanyWithReviewResponseDto;
import com.microservices.ReviewCompanyService.mapper.ReviewCompanyMapper;
import com.microservices.ReviewCompanyService.repository.IReviewCompanyRepository;
import com.microservices.ReviewCompanyService.service.IReviewCompanyService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class ReviewCompanyServiceImpl implements IReviewCompanyService {

    private final IReviewCompanyRepository reviewCompanyRepository;

    private final ICompanyReviewFeignService companyReviewFeignService;

    // Create a new review for a company
    @Override
    public ReviewCompanyResponseDto createReview(ReviewCompanyRequestDto reviewCompanyRequestDto) {
        log.info("Creating The ReviewCompany...");

        ReviewCompany reviewCompany
                = ReviewCompanyMapper.mapToReview(reviewCompanyRequestDto);
        ReviewCompany savedReviewcompany = reviewCompanyRepository.save(reviewCompany);

        log.info("ReviewCompany Is Created With companyId: {}", reviewCompany.getCompanyId());
        return ReviewCompanyMapper.mapToReviewResponseDto(savedReviewcompany);
    }

    // Retrieve all reviews for a specified company
    @Override
    public CompanyWithReviewResponseDto getAllReviewsByCompanyId(Long companyId) {
        log.info("Getting All Reviews For Specified companyId: {}", companyId);

        // Retrieve all reviews for the specified company from the repository
        List<ReviewCompany> reviewOfCompanies = reviewCompanyRepository.findAllByCompanyId(companyId);

        // Fetch company details using Feign client
        CompanyResponseDto companyResponseDto = companyReviewFeignService.getCompanyById(companyId);

        // Map each review to ReviewCompanyResponseDto
        List<ReviewCompanyResponseDto> reviewCompanyResponseDtos =  reviewOfCompanies.stream()
                .map(ReviewCompanyMapper::mapToReviewResponseDto)
                .toList();

        // Create a response DTO containing company details and associated reviews
        CompanyWithReviewResponseDto companyWithReviewResponseDto = CompanyWithReviewResponseDto.builder()
                .reviewCompanyResponseDto(reviewCompanyResponseDtos)
                .companyResponseDto(companyResponseDto)
                .build();

        log.info("All Reviews Are Listed.");
        return companyWithReviewResponseDto;
    }
    // Update a review for a specified company
    @Override
    public ReviewCompanyResponseDto updateCompanyReview(Long companyId,
                                                        ReviewCompanyRequestDto reviewCompanyRequestDto,
                                                        Long reviewId) {
        log.info("Updating ReviewCompany For Specified companyId: {}", companyId);

        // Retrieve all reviews for the specified company
        List<ReviewCompany> reviewCompanies = reviewCompanyRepository.findReviewByCompanyId(companyId);

        // Find the review to update by reviewId
        ReviewCompany foundedReviewCompany = reviewCompanies.stream()
                        .filter(reviewCompany -> reviewCompany.getId().equals(reviewId)).findFirst()
                        .orElseThrow(() -> new ResourceNotFoundException(
                                "Review Company not found with id: " + reviewId,
                                "Company_NOT_FOUND",
                                404));

        // Update the review details
        foundedReviewCompany.setCompanyComment(reviewCompanyRequestDto.getCompanyComment());
        foundedReviewCompany.setCompanyReview(reviewCompanyRequestDto.getCompanyReview());

        // Save the updated review in the repository
        ReviewCompany savedReviewcompany = reviewCompanyRepository.save(foundedReviewCompany);

        log.info("ReviewCompany Is Updated.");
        return ReviewCompanyMapper.mapToReviewResponseDto(savedReviewcompany);
    }

    // Delete a review for a specified company
    @Override
    public void deleteCompanyReview(Long companyId, Long reviewId) {
        log.info("ReviewCompany Is Getting Deleted For The Specified companyId: {}", companyId);

        // Retrieve all reviews for the specified company
        List<ReviewCompany> reviewCompanies = reviewCompanyRepository.findReviewByCompanyId(companyId);

        // Find the review to delete by reviewId
        ReviewCompany foundedReviewCompany = reviewCompanies.stream()
                .filter(reviewCompany -> reviewCompany.getId().equals(reviewId)).findFirst()
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Review Company not found with id: " + reviewId,
                        "Company_NOT_FOUND",
                        404));

        reviewCompanyRepository.delete(foundedReviewCompany);

        log.info("Review Company Is Deleted");
    }
}
