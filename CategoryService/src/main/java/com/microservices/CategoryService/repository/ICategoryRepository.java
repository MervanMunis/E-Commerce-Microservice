package com.microservices.CategoryService.repository;

import com.microservices.CategoryService.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findAllByCategoryName(String categoryName);

}
