package com.wanted.preonboardingbackend.repository;

import com.wanted.preonboardingbackend.entity.Apply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApplyRepository extends JpaRepository<Apply, Long> {
    Optional<Apply> findByRecruitIdAndMemberId (Long recruitId, Long MemberId);
}
