package com.microservices.CompanyService.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class CompanyRequestDto {

    private Long id;
    private String userName;
    private String companyName;
    private String location;
    private String industryType;
    private String contactPerson;
    private String contactEmail;
    private String contactPhone;
    private String foundedYear;
}
