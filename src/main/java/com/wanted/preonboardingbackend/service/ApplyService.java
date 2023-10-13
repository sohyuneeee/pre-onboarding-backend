package com.wanted.preonboardingbackend.service;

import com.wanted.preonboardingbackend.exception.CustomException;
import com.wanted.preonboardingbackend.exception.ErrorCode;
import com.wanted.preonboardingbackend.entity.Apply;
import com.wanted.preonboardingbackend.dto.responseDto.ApplyResponseDto;
import com.wanted.preonboardingbackend.repository.ApplyRepository;
import com.wanted.preonboardingbackend.entity.Member;
import com.wanted.preonboardingbackend.repository.MemberRepository;
import com.wanted.preonboardingbackend.entity.Recruit;
import com.wanted.preonboardingbackend.repository.RecruitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ApplyService {
    private final ApplyRepository applyRepository;
    private final RecruitRepository recruitRepository;
    private final MemberRepository memberRepository;

    public ApplyResponseDto apply (Long recruitId, Long memberId) {
        Recruit recruit = isPresentRecruit(recruitId);
        Member member = isPresentMember(memberId);
        Apply apply = new Apply(recruit, member);
        applyRepository.save(apply);
        return new ApplyResponseDto(apply);
    }

    public Recruit isPresentRecruit(Long id) {
        Optional<Recruit> recruit = recruitRepository.findById(id);
        return recruit.orElseThrow(() -> new CustomException(ErrorCode.RECRUITMENT_NOT_FOUND));
    }

    public Member isPresentMember(Long id) {
        Optional<Member> member = memberRepository.findById(id);
        return member.orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
    }
}
