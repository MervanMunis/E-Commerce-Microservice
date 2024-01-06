package com.microservices.ReviewCompanyService.external.client;

import com.microservices.ReviewCompanyService.external.response.CompanyResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("COMPANY-SERVICE/companies")
public interface ICompanyReviewFeignService {

    @RequestMapping(method = RequestMethod.GET, value = "/{companyId}")
    public CompanyResponseDto getCompanyById(@PathVariable("companyId") Long companyId);

}
