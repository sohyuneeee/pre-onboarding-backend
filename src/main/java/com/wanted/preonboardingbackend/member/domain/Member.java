package com.wanted.preonboardingbackend.member.domain;

import com.wanted.preonboardingbackend.member.dto.MemberRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Member {

    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    public Member (MemberRequestDto memberRequestDto) {
        this.username = memberRequestDto.getUsername();
        this.password = memberRequestDto.getPassword();
    }

}
