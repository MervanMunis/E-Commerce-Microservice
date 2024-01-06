package com.microservices.ReviewCompanyService.service;

import com.microservices.ReviewCompanyService.dto.ReviewCompanyRequestDto;
import com.microservices.ReviewCompanyService.dto.ReviewCompanyResponseDto;
import com.microservices.ReviewCompanyService.external.response.CompanyWithReviewResponseDto;

import java.util.List;

public interface IReviewCompanyService {

    ReviewCompanyResponseDto createReview(ReviewCompanyRequestDto reviewCompanyRequestDto);

    CompanyWithReviewResponseDto getAllReviewsByCompanyId(Long companyId);

    ReviewCompanyResponseDto updateCompanyReview(Long companyId,
                                                 ReviewCompanyRequestDto reviewCompanyRequestDto,
                                                 Long reviewId);

    void deleteCompanyReview(Long companyId, Long reviewId);
}
