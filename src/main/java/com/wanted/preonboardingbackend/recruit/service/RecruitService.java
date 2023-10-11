package com.wanted.preonboardingbackend.recruit.service;

import com.wanted.preonboardingbackend.CustomException;
import com.wanted.preonboardingbackend.ErrorCode;
import com.wanted.preonboardingbackend.company.domain.Company;
import com.wanted.preonboardingbackend.company.repository.CompanyRepository;
import com.wanted.preonboardingbackend.recruit.domain.Recruit;
import com.wanted.preonboardingbackend.recruit.dto.RecruitRequestDto;
import com.wanted.preonboardingbackend.recruit.dto.RecruitResponseDto;
import com.wanted.preonboardingbackend.recruit.repository.RecruitRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Builder
public class RecruitService {
    private final RecruitRepository recruitRepository;
    private final CompanyRepository companyRepository;

    @Transactional
    public RecruitResponseDto createRecruit(RecruitRequestDto recruitRequestDto) {
        Company company = isPresentCompany(recruitRequestDto.getCompanyId());
        Recruit recruit = recruitRepository.save(Recruit.builder()
                .company(company)
                .position(recruitRequestDto.getPosition())
                .reward(recruitRequestDto.getReward())
                .content(recruitRequestDto.getContent())
                .techStack(recruitRequestDto.getTechStack())
                .build());
        return new RecruitResponseDto(recruit);
    }

    @Transactional
    public RecruitResponseDto updateRecruit(Long id, RecruitRequestDto recruitRequestDto) {
        Company company = isPresentCompany(recruitRequestDto.getCompanyId());
        Recruit recruit = isPresentRecruit(id);

        if (!Objects.equals(company.getId(), recruit.getCompany().getId())) {
            throw new CustomException(ErrorCode.ENTITY_NOT_FOUND);
        }

        recruit.update(recruitRequestDto.getPosition(), recruitRequestDto.getReward(), recruitRequestDto.getContent(), recruitRequestDto.getTechStack());
        return new RecruitResponseDto(recruit);
    }

    public Company isPresentCompany(Long id) {
        Optional<Company> company = companyRepository.findById(id);
        return company.orElseThrow(() -> new CustomException(ErrorCode.ENTITY_NOT_FOUND));
    }

    public Recruit isPresentRecruit(Long id) {
        Optional<Recruit> recruit = recruitRepository.findById(id);
        return recruit.orElseThrow(() -> new CustomException(ErrorCode.ENTITY_NOT_FOUND));
    }

}
