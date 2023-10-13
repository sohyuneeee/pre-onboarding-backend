package com.wanted.preonboardingbackend.service;

import com.wanted.preonboardingbackend.exception.CustomException;
import com.wanted.preonboardingbackend.exception.ErrorCode;
import com.wanted.preonboardingbackend.entity.Company;
import com.wanted.preonboardingbackend.dto.requestDto.CompanyRequestDto;
import com.wanted.preonboardingbackend.dto.responseDto.CompanyResponseDto;
import com.wanted.preonboardingbackend.repository.CompanyRepository;
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
