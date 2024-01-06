package com.microservices.CategoryService.service;

import com.microservices.CategoryService.dto.CategoryRequestDto;
import com.microservices.CategoryService.dto.CategoryResponseDto;
import com.microservices.CategoryService.dto.SubcategoriesByCategoryDto;

import java.util.List;

public interface ICategoryService {

    CategoryResponseDto createCategory(CategoryRequestDto categoryRequestDto);

    List<CategoryResponseDto> getAllCategories();

    SubcategoriesByCategoryDto getAllSubcategoriesByCategoryName(String categoryName);

    CategoryResponseDto updateCategory(Long categoryId, CategoryRequestDto categoryRequestDto) ;

    void deleteCategory(Long categoryId);

}
