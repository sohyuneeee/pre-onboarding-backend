package com.wanted.preonboardingbackend.recruit.dto;

import com.wanted.preonboardingbackend.recruit.domain.Recruit;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RecruitListResponseDto {
    private Long id;
    private String companyName;
    private String country;
    private String city;
    private String position;
    private int reward;
    private String techStack;

    public RecruitListResponseDto (Recruit recruit) {
        this.id = recruit.getId();
        this.companyName = recruit.getCompany().getCompanyName();
        this.country = recruit.getCompany().getCountry();
        this.city = recruit.getCompany().getCity();
        this.position = recruit.getPosition();
        this.reward = recruit.getReward();
        this.techStack = recruit.getTechStack();
    }
}
