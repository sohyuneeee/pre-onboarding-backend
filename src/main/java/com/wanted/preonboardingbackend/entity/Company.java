package com.wanted.preonboardingbackend.entity;

import com.wanted.preonboardingbackend.dto.requestDto.CompanyRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Company {

    @Id
    @Column(name = "company_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String companyName;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String city;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Recruit> recruitList;

    public Company(CompanyRequestDto companyRequestDto) {
        this.companyName = companyRequestDto.getCompanyName();
        this.country = companyRequestDto.getCountry();
        this.city = companyRequestDto.getCity();
    }
}
