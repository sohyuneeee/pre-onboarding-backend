현package com.wanted.preonboardingbackend.company.service;

import com.wanted.preonboardingbackend.company.domain.Company;
import com.wanted.preonboardingbackend.company.dto.CompanyRequestDto;
import com.wanted.preonboardingbackend.company.dto.CompanyResponseDto;
import com.wanted.preonboardingbackend.company.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;

    @Transactional
    public CompanyResponseDto createCompany (CompanyRequestDto companyRequestDto) {
        Company company = new Company(companyRequestDto);
        company = companyRepository.save(company);
        return new CompanyResponseDto(company);
    }
}
