package com.wanted.preonboardingbackend.controller;

import com.wanted.preonboardingbackend.exception.CustomException;
import com.wanted.preonboardingbackend.exception.ErrorCode;
import com.wanted.preonboardingbackend.dto.responseDto.ResponseDto;
import com.wanted.preonboardingbackend.dto.responseDto.ApplyResponseDto;
import com.wanted.preonboardingbackend.service.ApplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ApplyController {
    private final ApplyService applyService;

    @PostMapping("api/apply/{recruitId}/{memberId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDto<ApplyResponseDto> apply(@PathVariable Long recruitId, @PathVariable Long memberId) {
        ApplyResponseDto applyResponseDto;
        try {
            applyResponseDto = applyService.apply(recruitId, memberId);
        } catch (CustomException e) {
            log.error(e.getMessage());
            return new ResponseDto<>(null, e.getErrorCode());
        }
        catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseDto<>(null, ErrorCode.INVALID_ERROR);
        }
        return new ResponseDto<>(applyResponseDto);
    }
}
