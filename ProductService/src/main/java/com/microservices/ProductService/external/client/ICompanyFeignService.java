package com.microservices.ProductService.external.client;

import com.microservices.ProductService.external.response.CompanyWithProductsResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "COMPANY-SERVICE/companies")
public interface ICompanyFeignService {

    @GetMapping("/{companyId}")
    CompanyWithProductsResponseDto getCompanyById(@PathVariable("companyId") Long companyId);

}
