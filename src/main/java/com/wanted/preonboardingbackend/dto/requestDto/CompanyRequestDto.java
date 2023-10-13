package com.wanted.preonboardingbackend.dto.requestDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyRequestDto {
    private String companyName;
    private String country;
    private String city;
}
