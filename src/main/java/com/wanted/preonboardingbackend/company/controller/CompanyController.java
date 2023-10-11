package com.wanted.preonboardingbackend.company.controller;

import com.wanted.preonboardingbackend.ErrorCode;
import com.wanted.preonboardingbackend.ResponseDto;
import com.wanted.preonboardingbackend.company.dto.CompanyRequestDto;
import com.wanted.preonboardingbackend.company.dto.CompanyResponseDto;
import com.wanted.preonboardingbackend.company.service.CompanyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CompanyController {
    private final CompanyService companyService;

    @PostMapping("/api/company")
    public ResponseDto<CompanyResponseDto> createCompany(@RequestBody CompanyRequestDto companyRequestDto) {
        CompanyResponseDto companyResponseDto;
        try {
            companyResponseDto = companyService.createCompany(companyRequestDto);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseDto<>(null, ErrorCode.INVALID_ERROR);
        }
        return new ResponseDto<>(companyResponseDto);
    }
}
