package com.microservices.ReviewProductService.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ErrorResponse {
    private String errorMessage;
    private String errorCode;
}
