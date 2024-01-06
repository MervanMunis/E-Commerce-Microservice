package com.microservices.ReviewProductService.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ReviewProductRequestDto {

    private Long id;
    private Long productId;
    private String productComment;

    // Between 1-5
    private int productReview;
}
