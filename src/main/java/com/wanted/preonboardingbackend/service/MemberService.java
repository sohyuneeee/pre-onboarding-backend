package com.wanted.preonboardingbackend.service;

import com.wanted.preonboardingbackend.exception.CustomException;
import com.wanted.preonboardingbackend.exception.ErrorCode;
import com.wanted.preonboardingbackend.entity.Member;
import com.wanted.preonboardingbackend.dto.requestDto.MemberRequestDto;
import com.wanted.preonboardingbackend.dto.responseDto.MemberResponseDto;
import com.wanted.preonboardingbackend.repository.MemberRepository;
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
            throw new CustomException(ErrorCode.DUPLICATION_USERNAME);
        }
    }
}
