package com.microservices.CategoryService.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class SubcategoriesByCategoryDto {

    private Long id;
    private String categoryName;
    private List<String> subcategories;
}
