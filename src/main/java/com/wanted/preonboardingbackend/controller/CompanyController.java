package com.wanted.preonboardingbackend.controller;

import com.wanted.preonboardingbackend.exception.CustomException;
import com.wanted.preonboardingbackend.exception.ErrorCode;
import com.wanted.preonboardingbackend.dto.responseDto.ResponseDto;
import com.wanted.preonboardingbackend.dto.requestDto.CompanyRequestDto;
import com.wanted.preonboardingbackend.dto.responseDto.CompanyResponseDto;
import com.wanted.preonboardingbackend.service.CompanyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CompanyController {
    private final CompanyService companyService;

    @PostMapping("/api/company")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDto<CompanyResponseDto> createCompany(@RequestBody CompanyRequestDto companyRequestDto) {
        CompanyResponseDto companyResponseDto;
        try {
            companyResponseDto = companyService.createCompany(companyRequestDto);
        }catch (CustomException e) {
            log.error(e.getMessage());
            return new ResponseDto<>(null, e.getErrorCode());
        }
        catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseDto<>(null, ErrorCode.INVALID_ERROR);
        }
        return new ResponseDto<>(companyResponseDto);
    }
}
