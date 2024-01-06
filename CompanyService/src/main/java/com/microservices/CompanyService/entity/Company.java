package com.microservices.CompanyService.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Table(name = "companies")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "COMPANY_NAME")
    private String companyName;

    @Column(name = "LOCATION")
    private String location;

    @Column(name = "INDUSTRY_TYPE")
    private String industryType;

    @Column(name = "CONTACT_PERSON")
    private String contactPerson;

    @Column(name = "CONTACT_EMAIL")
    private String contactEmail;

    @Column(name = "CONTACT_PHONE")
    private String contactPhone;

    @Column(name = "FOUNDED_YEAR")
    private String foundedYear;

    @Lob
    @Column(columnDefinition = "longblob")
    private byte[] image;
}
