package com.wanted.preonboardingbackend.company.repository;

import com.wanted.preonboardingbackend.company.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    Optional<Company> findByCompanyName(String companyName);
}
