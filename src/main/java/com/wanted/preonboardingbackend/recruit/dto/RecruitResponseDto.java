package com.wanted.preonboardingbackend.recruit.dto;

import com.wanted.preonboardingbackend.recruit.domain.Recruit;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RecruitResponseDto {
    private  Long recruitId;
    private Long companyId;
    private  String position;
    private  int reward;
    private  String content;
    private  String techStack;

    public RecruitResponseDto(Recruit recruit) {
        this.recruitId = recruit.getId();
        this.companyId = recruit.getCompany().getId();
        this.position = recruit.getPosition();
        this.reward = recruit.getReward();
        this.content = recruit.getContent();
        this.techStack = recruit.getTechStack();
    }
}


