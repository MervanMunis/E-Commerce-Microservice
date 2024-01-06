package com.microservices.ReviewCompanyService.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ReviewCompanyRequestDto {

    private Long id;
    private Long companyId;
    private String companyComment;

    // Between 1-5
    private int companyReview;
}
