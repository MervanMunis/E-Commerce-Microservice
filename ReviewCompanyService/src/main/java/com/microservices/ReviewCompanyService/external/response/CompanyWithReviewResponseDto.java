package com.microservices.ReviewCompanyService.external.response;

import com.microservices.ReviewCompanyService.dto.ReviewCompanyResponseDto;
import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CompanyWithReviewResponseDto {

    private CompanyResponseDto companyResponseDto;

    private List<ReviewCompanyResponseDto> reviewCompanyResponseDto;

}
