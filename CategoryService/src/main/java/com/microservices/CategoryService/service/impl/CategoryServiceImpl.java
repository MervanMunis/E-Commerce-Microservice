package com.microservices.CategoryService.service.impl;

import com.microservices.CategoryService.dto.CategoryRequestDto;
import com.microservices.CategoryService.dto.CategoryResponseDto;
import com.microservices.CategoryService.dto.SubcategoriesByCategoryDto;
import com.microservices.CategoryService.entity.Category;
import com.microservices.CategoryService.exception.ResourceNotFoundException;
import com.microservices.CategoryService.mapper.CategoryMapper;
import com.microservices.CategoryService.repository.ICategoryRepository;
import com.microservices.CategoryService.service.ICategoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@Service
public class CategoryServiceImpl implements ICategoryService {

    // Injecting the Category Repository via constructor for dependency injection
    private final ICategoryRepository categoryRepository;

    // Creating a new Category
    @Override
    public CategoryResponseDto createCategory(CategoryRequestDto categoryRequestDto) {
        log.info("Creating a Category...");
        Category category = CategoryMapper.mapToCategory(categoryRequestDto);
        Category savedCategory = categoryRepository.save(category);
        log.info("Category Is Created: {} ", savedCategory.getId());
        return CategoryMapper.mapToCategoryResponseDto(savedCategory);
    }

    // Retrieving all categories
    @Override
    public List<CategoryResponseDto> getAllCategories() {
        log.info("Getting All Categories...");
        List<Category> categories = categoryRepository.findAll();

        log.info("Categories are Listed.");
        return categories.stream().map(CategoryMapper::mapToCategoryResponseDto)
                .collect(Collectors.toList());
    }

    // Retrieving subcategories by category name
    @Override
    public SubcategoriesByCategoryDto getAllSubcategoriesByCategoryName(String categoryName) {
        log.info("Getting All The Subcategories By Category Name: {}", categoryName);
        List<Category> categories = categoryRepository.findAllByCategoryName(categoryName);

        log.info("Listing All The Subcategories");
        List<String> subcategories = categories.stream()
                .map(Category::getSubcategory)
                .toList();

        log.info("Subcategories Are Listed.");
        return CategoryMapper.mapToSubcategoriesByCategoryDto(categoryName, subcategories);
    }

    // Updating an existing category
    @Override
    public CategoryResponseDto updateCategory(Long categoryId, CategoryRequestDto categoryRequestDto) {
        log.info("Finding The Category To Update With: {}", categoryId);
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Company Does Not Exist With The Given Id: "  + categoryId,
                        "COMPANY_NOT_FOUND",
                        404)
                );
        log.info("Updating The Category.");
        category.setCategoryName(categoryRequestDto.getCategoryName());
        category.setSubcategory(categoryRequestDto.getSubcategory());
        log.info("Category Is Updated.");
        return CategoryMapper.mapToCategoryResponseDto(category);
    }

    // Deleting a category
    @Override
    public void deleteCategory(Long categoryId) {
        log.info("Deleting The Category With categoryId: {}", categoryId);

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Company Does Not Exist With The Given Id: "  + categoryId,
                        "COMPANY_NOT_FOUND",
                        404)
                );
        categoryRepository.delete(category);
        log.info("Category Is Deleted");
    }
}
