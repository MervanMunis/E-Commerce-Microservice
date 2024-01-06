package com.microservices.CompanyService.service;

import com.microservices.CompanyService.dto.CompanyRequestDto;
import com.microservices.CompanyService.dto.CompanyResponseDto;

import java.util.List;
public interface ICompanyService {

    CompanyResponseDto createCompany(CompanyRequestDto companyRequestDto);

    CompanyResponseDto getCompanyById(Long companyId);

    List<CompanyResponseDto> getAllCompanies();

    CompanyResponseDto updateCompany(Long companyId, CompanyRequestDto companyRequestDto);

    String deleteCompany(Long companyId);

}
