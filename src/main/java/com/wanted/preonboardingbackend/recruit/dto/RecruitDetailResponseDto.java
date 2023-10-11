package com.wanted.preonboardingbackend.recruit.dto;

import com.wanted.preonboardingbackend.recruit.domain.Recruit;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class RecruitDetailResponseDto {
    private Long id;
    private String companyName;
    private String country;
    private String city;
    private String position;
    private int reward;
    private String techStack;
    private String content;

//    private final List<Long> recruitList = new ArrayList<>();

    public RecruitDetailResponseDto (Recruit recruit) {
        this.id = recruit.getId();
        this.companyName = recruit.getCompany().getCompanyName();
        this.country = recruit.getCompany().getCountry();
        this.city = recruit.getCompany().getCity();
        this.position = recruit.getPosition();
        this.reward = recruit.getReward();
        this.techStack = recruit.getTechStack();
        this.content = recruit.getContent();

    }
}
