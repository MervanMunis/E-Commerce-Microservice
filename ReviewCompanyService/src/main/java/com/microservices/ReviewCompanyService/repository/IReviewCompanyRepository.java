package com.microservices.ReviewCompanyService.repository;

import com.microservices.ReviewCompanyService.entity.ReviewCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IReviewCompanyRepository extends JpaRepository<ReviewCompany, Long> {

    List<ReviewCompany> findAllByCompanyId(Long productId);

    List<ReviewCompany> findReviewByCompanyId(Long productId);
}
