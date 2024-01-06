package com.microservices.CompanyService.service.impl;

import com.microservices.CompanyService.dto.CompanyRequestDto;
import com.microservices.CompanyService.dto.CompanyResponseDto;
import com.microservices.CompanyService.entity.Company;
import com.microservices.CompanyService.exception.ResourceNotFoundException;
import com.microservices.CompanyService.mapper.CompanyMapper;
import com.microservices.CompanyService.repository.ICompanyRepository;
import com.microservices.CompanyService.service.ICompanyService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
@Log4j2
public class CompanyServiceImpl implements ICompanyService {

    private final ICompanyRepository companyRepository;

    // Creating a new company
    @Override
    public CompanyResponseDto createCompany(CompanyRequestDto companyRequestDto) {
        log.info("Creating The Company...");

        Company company = CompanyMapper.mapToCompany(companyRequestDto);

        log.info("Checking If The Company Exists Or Not");
        boolean isExists = companyRepository.existsById(company.getId());

        if (!isExists){
            log.info("Saving Company...");

            Company savedCompany = companyRepository.save(company);
            log.info("Company Created.");

            return CompanyMapper.mapToCompanyResponseDto(savedCompany);
        }else {
            log.info("Company Already Exists.");
            return null;
        }
    }

    @Override
    public CompanyResponseDto getCompanyById(Long companyId) {
        log.info("Get Company For The companyId: {}", companyId);

        Company company = companyRepository.findById(companyId).orElseThrow(
                () -> new ResourceNotFoundException(
                        "Company Does Not Exist With The Given Id: "  + companyId,
                        "COMPANY_NOT_FOUND",
                        404)
                );

        return CompanyMapper.mapToCompanyResponseDto(company);
    }

    // Retrieving a company by its ID
    @Override
    public List<CompanyResponseDto> getAllCompanies() {
        log.info("Finding All Companies.");

        List<Company> companies = companyRepository.findAll();

        log.info("All Companies Are Listed.");
        return companies.stream().map(CompanyMapper::mapToCompanyResponseDto)
                .collect(Collectors.toList());
    }

    // Updating an existing company
    @Override
    public CompanyResponseDto updateCompany(Long companyId, CompanyRequestDto companyRequestDto) {
        log.info("Finding The Company To Update...");

        Company company = companyRepository.findById(companyId).orElseThrow(
                () -> new ResourceNotFoundException(
                        "Company Does Not Exist With The Given Id: " + companyId,
                        "COMPANY_NOT_FOUND",
                        404)
        );

        company.setCompanyName(companyRequestDto.getCompanyName());
        company.setUserName(companyRequestDto.getUserName());
        company.setLocation(companyRequestDto.getLocation());
        company.setIndustryType(companyRequestDto.getIndustryType());
        company.setContactPhone(companyRequestDto.getContactPhone());
        company.setContactEmail(companyRequestDto.getContactEmail());
        company.setContactPhone(companyRequestDto.getContactPhone());
        company.setFoundedYear(companyRequestDto.getFoundedYear());
        companyRepository.save(company);

        log.info("Company is updated.");
        return CompanyMapper.mapToCompanyResponseDto(company);
    }

    // Deleting a company
    @Override
    public String deleteCompany(Long companyId) {
        log.info("Deleting TheCompany...");
        Company company = companyRepository.findById(companyId).orElseThrow(
                () -> new ResourceNotFoundException(
                        "Company Does Not Exist With The Given Id: " + companyId,
                        "COMPANY_NOT_FOUND",
                        404)
        );

        companyRepository.delete(company);
        log.info("Company Is Deleted: {}",company.getCompanyName());
        return "Company is Deleted";
    }

}
