package com.microservices.ReviewCompanyService.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "COMMENT_REVIEW_COMPANY")
public class ReviewCompany {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "PRODUCT_ID")
    private Long companyId;

    @Column(name = "COMPANY_COMMENT", columnDefinition = "TEXT")
    private String companyComment;

    // Between 1-5
    @Column(name = "COMPANY_REVIEW")
    private int companyReview;

}
