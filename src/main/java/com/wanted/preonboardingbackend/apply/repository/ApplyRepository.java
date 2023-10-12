package com.wanted.preonboardingbackend.apply.repository;

import com.wanted.preonboardingbackend.apply.domain.Apply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplyRepository extends JpaRepository<Apply, Long> {
}
