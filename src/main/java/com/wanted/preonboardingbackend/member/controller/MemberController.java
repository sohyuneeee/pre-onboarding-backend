package com.wanted.preonboardingbackend.member.controller;

import com.wanted.preonboardingbackend.CustomException;
import com.wanted.preonboardingbackend.ErrorCode;
import com.wanted.preonboardingbackend.ResponseDto;
import com.wanted.preonboardingbackend.member.dto.MemberRequestDto;
import com.wanted.preonboardingbackend.member.dto.MemberResponseDto;
import com.wanted.preonboardingbackend.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    private final MemberService memberService;
    @PostMapping("api/member")
    public ResponseDto<MemberResponseDto> signup (@RequestBody MemberRequestDto memberRequestDto) {
        MemberResponseDto memberResponseDto;
        try {
            memberResponseDto = memberService.signup(memberRequestDto);
        } catch (CustomException e) {
            log.error(e.getMessage());
            return new ResponseDto<>(null, ErrorCode.DUPLICATION_NICKNAME);
        }
        catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseDto<>(null, ErrorCode.INVALID_ERROR);
        }
        return new ResponseDto<>(memberResponseDto);
    }
}
