package com.wanted.preonboardingbackend.member.service;

import com.wanted.preonboardingbackend.CustomException;
import com.wanted.preonboardingbackend.ErrorCode;
import com.wanted.preonboardingbackend.member.domain.Member;
import com.wanted.preonboardingbackend.member.dto.MemberRequestDto;
import com.wanted.preonboardingbackend.member.dto.MemberResponseDto;
import com.wanted.preonboardingbackend.member.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    public MemberResponseDto signup (MemberRequestDto memberRequestDto) {
        checkDuplicationUsername(memberRequestDto.getUsername());
        Member member = new Member(memberRequestDto);
        member = memberRepository.save(member);
        return new MemberResponseDto(member);
    }

    private void checkDuplicationUsername(String username) {
        Optional<Member> optionalMember = memberRepository.findByUsername(username);
        if(optionalMember.isPresent()) {
            throw new CustomException(ErrorCode.DUPLICATION_NICKNAME);
        }
    }
}
