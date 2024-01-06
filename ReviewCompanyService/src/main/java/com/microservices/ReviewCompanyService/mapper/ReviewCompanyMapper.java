package com.microservices.ReviewCompanyService.mapper;

import com.microservices.ReviewCompanyService.dto.ReviewCompanyRequestDto;
import com.microservices.ReviewCompanyService.dto.ReviewCompanyResponseDto;
import com.microservices.ReviewCompanyService.entity.ReviewCompany;
import com.microservices.ReviewCompanyService.external.response.CompanyResponseDto;
import com.microservices.ReviewCompanyService.external.response.CompanyWithReviewResponseDto;

public class ReviewCompanyMapper {

    // Map ReviewCompany entity to ReviewCompanyResponseDto
    public static ReviewCompanyResponseDto mapToReviewResponseDto(ReviewCompany reviewCompany) {
        return ReviewCompanyResponseDto.builder()
                .id(reviewCompany.getId())
                .companyId(reviewCompany.getCompanyId())
                .companyComment(reviewCompany.getCompanyComment())
                .companyReview(reviewCompany.getCompanyReview())
                .build();
    }

    // Map ReviewCompanyRequestDto to ReviewCompany entity
    public static ReviewCompany mapToReview(ReviewCompanyRequestDto reviewCompanyRequestDto) {
        return ReviewCompany.builder()
                .companyId(reviewCompanyRequestDto.getCompanyId())
                .companyComment(reviewCompanyRequestDto.getCompanyComment())
                .companyReview(reviewCompanyRequestDto.getCompanyReview())
                .build();
    }
}
