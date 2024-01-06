package com.microservices.CategoryService.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "CATEGORIES")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CATEGORY_NAME")
    @NotBlank(message = "Category name cannot be empty")
    private String categoryName;

    @Column(name = "SUBCATEGORY")
    private String subcategory;
}
