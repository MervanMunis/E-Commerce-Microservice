package com.microservices.CompanyService.mapper;

import com.microservices.CompanyService.dto.CompanyRequestDto;
import com.microservices.CompanyService.dto.CompanyResponseDto;
import com.microservices.CompanyService.entity.Company;

import java.time.Instant;

public class CompanyMapper {

    // Mapping Company entity to CompanyResponseDto
    public static CompanyResponseDto mapToCompanyResponseDto(Company company){
        return CompanyResponseDto.builder()
                .id(company.getId())
                .companyName(company.getCompanyName())
                .userName(company.getUserName())
                .location(company.getLocation())
                .industryType(company.getIndustryType())
                .contactPerson(company.getContactPerson())
                .contactEmail(company.getContactEmail())
                .contactPhone(company.getContactPhone())
                .foundedYear(company.getFoundedYear())
                .build();
    }

    // Mapping CompanyRequestDto to Company entity
    public static Company mapToCompany(CompanyRequestDto companyRequestDto){
        return Company.builder()
                .id(companyRequestDto.getId())
                .companyName(companyRequestDto.getCompanyName())
                .userName(companyRequestDto.getUserName())
                .location(companyRequestDto.getLocation())
                .industryType(companyRequestDto.getIndustryType())
                .contactPerson(companyRequestDto.getContactPerson())
                .contactEmail(companyRequestDto.getContactEmail())
                .contactPhone(companyRequestDto.getContactPhone())
                .foundedYear(companyRequestDto.getFoundedYear())
                .build();
    }
}
