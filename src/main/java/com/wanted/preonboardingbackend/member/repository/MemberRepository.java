package com.wanted.preonboardingbackend.member.repository;

import com.wanted.preonboardingbackend.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByUsername(String username);
}
