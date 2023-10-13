package com.wanted.preonboardingbackend.dto.responseDto;

import com.wanted.preonboardingbackend.entity.Member;
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
