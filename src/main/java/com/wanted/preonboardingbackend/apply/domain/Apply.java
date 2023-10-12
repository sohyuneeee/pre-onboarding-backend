package com.wanted.preonboardingbackend.apply.domain;

import com.wanted.preonboardingbackend.member.domain.Member;
import com.wanted.preonboardingbackend.recruit.domain.Recruit;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Apply {

    @Id
    @Column(name = "apply_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recruid_id", nullable = false)
    private Recruit recruit;

}
