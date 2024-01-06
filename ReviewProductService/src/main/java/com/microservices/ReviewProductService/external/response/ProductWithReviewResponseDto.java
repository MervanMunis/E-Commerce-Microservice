package com.microservices.ReviewProductService.external.response;

import com.microservices.ReviewProductService.dto.ReviewProductResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductWithReviewResponseDto {

    private ProductResponseDto productResponseDto;

    private List<ReviewProductResponseDto> reviewProductResponseDtos;
}
