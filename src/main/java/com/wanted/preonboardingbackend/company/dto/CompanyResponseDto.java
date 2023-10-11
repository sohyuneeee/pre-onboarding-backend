package com.wanted.preonboardingbackend.company.dto;

import com.wanted.preonboardingbackend.company.domain.Company;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CompanyResponseDto {
    private Long companyId;
    private String companyName;
    private String country;
    private String city;

    public CompanyResponseDto (Company company) {
        this.companyId = company.getId();
        this.companyName = company.getCompanyName();
        this.country = company.getCountry();
        this.city = company.getCity();
    }
}
