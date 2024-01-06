package com.microservices.CompanyService.controller;

import com.microservices.CompanyService.dto.CompanyRequestDto;
import com.microservices.CompanyService.dto.CompanyResponseDto;
import com.microservices.CompanyService.service.ICompanyService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/companies")
public class CompanyController {

    private final ICompanyService companyService;

    // Creating a new company
    @PostMapping
    public ResponseEntity<CompanyResponseDto> createCompany(@RequestBody CompanyRequestDto companyRequestDto) {
        CompanyResponseDto responseDto = companyService.createCompany(companyRequestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    // Retrieving a company by its ID
    @GetMapping("/{companyId}")
    public ResponseEntity<CompanyResponseDto> getCompanyById(@PathVariable("companyId") Long companyId) {
        CompanyResponseDto responseDto = companyService.getCompanyById(companyId);
        return ResponseEntity.ok(responseDto);
    }

    // Retrieving all companies
    @GetMapping
    public ResponseEntity<List<CompanyResponseDto>> getAllCompanies() {
        List<CompanyResponseDto> responseDtoList = companyService.getAllCompanies();
        return ResponseEntity.ok(responseDtoList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompanyResponseDto> updateCompany(@PathVariable("id") Long companyId,
                                                            @RequestBody CompanyRequestDto companyRequestDto){
        CompanyResponseDto responseDto = companyService.updateCompany(companyId, companyRequestDto);

        return ResponseEntity.ok(responseDto);
    }

    // Updating an existing company
    @DeleteMapping("/{companyId}")
    public ResponseEntity<String> deleteCompany(@PathVariable("companyId") Long companyId) {
        String responseMessage = companyService.deleteCompany(companyId);
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }



}
