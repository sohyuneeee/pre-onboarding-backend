package com.wanted.preonboardingbackend.entity;

import com.wanted.preonboardingbackend.dto.requestDto.RecruitRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
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

    public Recruit(RecruitRequestDto recruitRequestDto, Company company) {
        this.position = recruitRequestDto.getPosition();
        this.reward = recruitRequestDto.getReward();
        this.content = recruitRequestDto.getContent();
        this.techStack = recruitRequestDto.getTechStack();
        this.company = company;
    }

    public void update(String position, int reward, String content, String techStack) {
        this.position = position;
        this.reward = reward;
        this.content = content;
        this.techStack = techStack;
    }
}
