package com.wanted.preonboardingbackend.dto.responseDto;

import com.wanted.preonboardingbackend.entity.Recruit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RecruitDetailResponseDto {
    private Long recruitId;
    private String companyName;
    private String country;
    private String city;
    private String position;
    private int reward;
    private String techStack;
    private String content;
    private List<Long> recruitIdList;

    public RecruitDetailResponseDto(List<Long> recruitIdList, Recruit recruit) {
        this.recruitId = recruit.getId();
        this.companyName = recruit.getCompany().getCompanyName();
        this.country = recruit.getCompany().getCountry();
        this.city = recruit.getCompany().getCity();
        this.position = recruit.getPosition();
        this.reward = recruit.getReward();
        this.techStack = recruit.getTechStack();
        this.content = recruit.getContent();
        this.recruitIdList = recruitIdList;
    }
}
