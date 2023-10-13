package com.wanted.preonboardingbackend.controller;

import com.wanted.preonboardingbackend.exception.CustomException;
import com.wanted.preonboardingbackend.exception.ErrorCode;
import com.wanted.preonboardingbackend.dto.responseDto.ResponseDto;
import com.wanted.preonboardingbackend.dto.requestDto.MemberRequestDto;
import com.wanted.preonboardingbackend.dto.responseDto.MemberResponseDto;
import com.wanted.preonboardingbackend.service.MemberService;
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
    public ResponseDto<MemberResponseDto> signup(@RequestBody MemberRequestDto memberRequestDto) {
        MemberResponseDto memberResponseDto;
        try {
            memberResponseDto = memberService.signup(memberRequestDto);
        } catch (CustomException e) {
            log.error(e.getMessage());
            return new ResponseDto<>(null, e.getErrorCode());
        }
        catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseDto<>(null, ErrorCode.INVALID_ERROR);
        }
        return new ResponseDto<>(memberResponseDto);
    }
}
