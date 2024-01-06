package com.microservices.CategoryService.mapper;

import com.microservices.CategoryService.dto.CategoryRequestDto;
import com.microservices.CategoryService.dto.CategoryResponseDto;
import com.microservices.CategoryService.dto.SubcategoriesByCategoryDto;
import com.microservices.CategoryService.entity.Category;

import java.util.List;

public class CategoryMapper {

    // Mapping CategoryRequestDto to Category entity
    public static Category mapToCategory(CategoryRequestDto categoryRequestDto) {
        return Category.builder()
                .categoryName(categoryRequestDto.getCategoryName())
                .subcategory(categoryRequestDto.getSubcategory())
                .build();
    }

    // Mapping Category entity to CategoryResponseDto
    public static CategoryResponseDto mapToCategoryResponseDto(Category category){
        return CategoryResponseDto.builder()
                .id(category.getId())
                .categoryName(category.getCategoryName())
                .subcategory(category.getSubcategory())
                .build();
    }

    // Mapping category name and subcategories to SubcategoriesByCategoryDto
    public static SubcategoriesByCategoryDto mapToSubcategoriesByCategoryDto(String categoryName, List<String> subcategories) {
        return SubcategoriesByCategoryDto.builder()
                .categoryName(categoryName)
                .subcategories(subcategories)
                .build();
    }
}
