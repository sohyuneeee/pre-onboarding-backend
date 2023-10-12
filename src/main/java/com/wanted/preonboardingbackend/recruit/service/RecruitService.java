package com.wanted.preonboardingbackend.recruit.service;

import com.wanted.preonboardingbackend.CustomException;
import com.wanted.preonboardingbackend.ErrorCode;
import com.wanted.preonboardingbackend.company.domain.Company;
import com.wanted.preonboardingbackend.company.repository.CompanyRepository;
import com.wanted.preonboardingbackend.recruit.domain.Recruit;
import com.wanted.preonboardingbackend.recruit.dto.RecruitDetailResponseDto;
import com.wanted.preonboardingbackend.recruit.dto.RecruitListResponseDto;
import com.wanted.preonboardingbackend.recruit.dto.RecruitRequestDto;
import com.wanted.preonboardingbackend.recruit.dto.RecruitResponseDto;
import com.wanted.preonboardingbackend.recruit.repository.RecruitRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
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
        Recruit recruit = new Recruit(recruitRequestDto, company);
        recruitRepository.save(recruit);
        return new RecruitResponseDto(recruit);
    }

    @Transactional
    public RecruitResponseDto updateRecruit(Long id, RecruitRequestDto recruitRequestDto) {
        Company company = isPresentCompany(recruitRequestDto.getCompanyId());
        Recruit recruit = isPresentRecruit(id);

        if (!Objects.equals(company.getId(), recruit.getCompany().getId())) {
            throw new CustomException(ErrorCode.ENTITY_NOT_FOUND);
        }

        recruit.update(recruitRequestDto.getPosition(), recruitRequestDto.getReward(),
                recruitRequestDto.getContent(), recruitRequestDto.getTechStack());
        return new RecruitResponseDto(recruit);
    }

    public void deleteRecruit (Long id) {
        Recruit recruit = isPresentRecruit(id);
        recruitRepository.deleteById(recruit.getId());
    }

    public List<RecruitListResponseDto>  getAllRecruit() {
        List<Recruit> recruitList = recruitRepository.findAll();
        return recruitListResponseDtoList(recruitList);
    }

    public RecruitDetailResponseDto getRecruit(Long id) {
        Recruit recruit = isPresentRecruit(id);
        List<Long> recruitIdList = recruitRepository.findRecruitId(recruit.getCompany().getId());
        return new RecruitDetailResponseDto(recruitIdList, recruit);
    }

    public List<RecruitListResponseDto> searchRecruit (String keyword) {
        List<Recruit> recruitList = recruitRepository.findAllByKeyword(keyword);
        return recruitListResponseDtoList(recruitList);
    }

    public Company isPresentCompany(Long id) {
        Optional<Company> company = companyRepository.findById(id);
        return company.orElseThrow(() -> new CustomException(ErrorCode.COMPANY_NOT_FOUND));
    }

    public Recruit isPresentRecruit(Long id) {
        Optional<Recruit> recruit = recruitRepository.findById(id);
        return recruit.orElseThrow(() -> new CustomException(ErrorCode.RECRUITMENT_NOT_FOUND));
    }

    private static List<RecruitListResponseDto> recruitListResponseDtoList(List<Recruit> recruitList) {
        List<RecruitListResponseDto> responseDtoList = new ArrayList<>();
        for(Recruit recruit : recruitList) {
            RecruitListResponseDto responseDto = RecruitListResponseDto.builder()
                    .recruitId(recruit.getId())
                    .companyName(recruit.getCompany().getCompanyName())
                    .country(recruit.getCompany().getCountry())
                    .city(recruit.getCompany().getCity())
                    .position(recruit.getPosition())
                    .reward(recruit.getReward())
                    .techStack(recruit.getTechStack())
                    .build();
            responseDtoList.add(responseDto);
        }
        return responseDtoList;
    }
}
