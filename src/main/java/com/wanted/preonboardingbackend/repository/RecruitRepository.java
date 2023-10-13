package com.wanted.preonboardingbackend.repository;

import com.wanted.preonboardingbackend.entity.Recruit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface RecruitRepository extends JpaRepository<Recruit, Long> {
    @Query("SELECT A.id FROM Recruit A WHERE A.company.id = :companyId")
    List<Long> findRecruitId(@Param("companyId") Long companyId);
    @Query("SELECT A FROM Recruit A WHERE A.company.companyName LIKE %:keyword% OR A.position LIKE %:keyword% OR A.techStack LIKE %:keyword%")
    List<Recruit> findAllByKeyword(@Param("keyword") String keyword);
}
