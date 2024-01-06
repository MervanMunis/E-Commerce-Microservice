package com.microservices.CategoryService.dto;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class CategoryResponseDto {

    private Long id;
    private String categoryName;
    private String subcategory;
}
