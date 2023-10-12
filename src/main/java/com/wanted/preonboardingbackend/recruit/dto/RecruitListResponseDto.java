package com.wanted.preonboardingbackend.recruit.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecruitListResponseDto {
    private Long recruitId;
    private String companyName;
    private String country;
    private String city;
    private String position;
    private int reward;
    private String techStack;
}
