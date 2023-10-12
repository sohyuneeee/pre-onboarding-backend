package com.wanted.preonboardingbackend.member.dto;

import com.wanted.preonboardingbackend.member.domain.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberResponseDto {
    private Long id;

    public MemberResponseDto(Member member) {
        this.id = member.getId();
    }
}
