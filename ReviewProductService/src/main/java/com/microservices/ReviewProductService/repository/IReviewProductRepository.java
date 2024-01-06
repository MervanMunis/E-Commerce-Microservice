package com.microservices.ReviewProductService.repository;

import com.microservices.ReviewProductService.entity.ReviewProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IReviewProductRepository extends JpaRepository<ReviewProduct, Long> {

    List<ReviewProduct> findAllByProductId(Long productId);

    List<ReviewProduct> findReviewByProductId(Long productId);
}
