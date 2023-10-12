package com.wanted.preonboardingbackend.company.service;

import com.wanted.preonboardingbackend.CustomException;
import com.wanted.preonboardingbackend.ErrorCode;
import com.wanted.preonboardingbackend.company.domain.Company;
import com.wanted.preonboardingbackend.company.dto.CompanyRequestDto;
import com.wanted.preonboardingbackend.company.dto.CompanyResponseDto;
import com.wanted.preonboardingbackend.company.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;

    @Transactional
    public CompanyResponseDto createCompany (CompanyRequestDto companyRequestDto) {
        checkDuplicationCompany(companyRequestDto.getCompanyName());
        Company company = new Company(companyRequestDto);
        company = companyRepository.save(company);
        return new CompanyResponseDto(company);
    }

    private void checkDuplicationCompany(String companyName) {
        Optional<Company> optionalCompany = companyRepository.findByCompanyName(companyName);
        if(optionalCompany.isPresent()) {
            throw new CustomException(ErrorCode.DUPLICATION_COMPANY);
        }
    }
}
