package com.wanted.preonboardingbackend.dto.requestDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RecruitRequestDto {
    private Long companyId;
    private String position;
    private int reward;
    private String content;
    private String techStack;
}
