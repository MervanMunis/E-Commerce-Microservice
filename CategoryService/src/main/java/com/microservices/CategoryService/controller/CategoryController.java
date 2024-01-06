package com.microservices.CategoryService.controller;

import com.microservices.CategoryService.dto.CategoryRequestDto;
import com.microservices.CategoryService.dto.CategoryResponseDto;
import com.microservices.CategoryService.dto.SubcategoriesByCategoryDto;
import com.microservices.CategoryService.service.ICategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final ICategoryService categoryService;

    // Creating a new category
    @PostMapping
    private ResponseEntity<CategoryResponseDto> createCategory(@RequestBody CategoryRequestDto categoryRequestDto) {
        CategoryResponseDto categoryResponseDto = categoryService.createCategory(categoryRequestDto);
        return new ResponseEntity<>(categoryResponseDto, HttpStatus.CREATED);
    }

    // Retrieving all categories
    @GetMapping
    private ResponseEntity<List<CategoryResponseDto>> getAllCategories(){
        List<CategoryResponseDto> categoryResponseDtos = categoryService.getAllCategories();
        return ResponseEntity.ok(categoryResponseDtos);
    }

    // Retrieving subcategories by category name
    // http://localhost:9090/categories/subcategories?categoryName=Fashion (Request)
    @GetMapping("/subcategories")
    public ResponseEntity<SubcategoriesByCategoryDto> getAllSubcategoriesByCategoryName(@RequestParam String categoryName){
        SubcategoriesByCategoryDto subcategories = categoryService.getAllSubcategoriesByCategoryName(categoryName);
        return ResponseEntity.ok(subcategories);
    }

    // Updating an existing category
    @PutMapping("/{id}")
    private ResponseEntity<CategoryResponseDto> updateCategory(@PathVariable("id") Long categoryId,
                                                               @RequestBody CategoryRequestDto categoryRequestDto) {
        CategoryResponseDto categoryResponseDto = categoryService.updateCategory(categoryId, categoryRequestDto);
        return new ResponseEntity<>(categoryResponseDto, HttpStatus.CREATED);
    }

    // Deleting a category
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") Long categoryId){
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.ok("Category is deleted");
    }

}
