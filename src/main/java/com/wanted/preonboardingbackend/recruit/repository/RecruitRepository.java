package com.wanted.preonboardingbackend.recruit.repository;

import com.wanted.preonboardingbackend.recruit.domain.Recruit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecruitRepository extends JpaRepository<Recruit, Long> {
}
