package com.wanted.preonboardingbackend.apply.service;

import com.wanted.preonboardingbackend.CustomException;
import com.wanted.preonboardingbackend.ErrorCode;
import com.wanted.preonboardingbackend.apply.domain.Apply;
import com.wanted.preonboardingbackend.apply.dto.ApplyResponseDto;
import com.wanted.preonboardingbackend.apply.repository.ApplyRepository;
import com.wanted.preonboardingbackend.member.domain.Member;
import com.wanted.preonboardingbackend.member.repository.MemberRepository;
import com.wanted.preonboardingbackend.recruit.domain.Recruit;
import com.wanted.preonboardingbackend.recruit.repository.RecruitRepository;
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
