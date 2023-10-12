package com.wanted.preonboardingbackend.recruit.domain;

import com.wanted.preonboardingbackend.apply.domain.Apply;
import com.wanted.preonboardingbackend.company.domain.Company;
import com.wanted.preonboardingbackend.recruit.dto.RecruitRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Recruit {

    @Id
    @Column(name = "recruit_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String position;

    @Column(nullable = true)
    private int reward;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String techStack;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToMany(mappedBy = "recruit", cascade = CascadeType.REMOVE)
    private List<Apply> applyList;

    public Recruit(RecruitRequestDto recruitRequestDto) {
        this.position = recruitRequestDto.getPosition();
        this.reward = recruitRequestDto.getReward();
        this.content = recruitRequestDto.getContent();
        this.techStack = recruitRequestDto.getTechStack();
    }

    public void update(String position, int reward, String content, String techStack) {
        this.position = position;
        this.reward = reward;
        this.content = content;
        this.techStack = techStack;
    }
}
