package com.wanted.preonboardingbackend.apply.controller;

import com.wanted.preonboardingbackend.CustomException;
import com.wanted.preonboardingbackend.ErrorCode;
import com.wanted.preonboardingbackend.ResponseDto;
import com.wanted.preonboardingbackend.apply.dto.ApplyResponseDto;
import com.wanted.preonboardingbackend.apply.service.ApplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ApplyController {
    private final ApplyService applyService;

    @PostMapping("api/apply/{recruitId}/{memberId}")
    public ResponseDto<ApplyResponseDto> apply (@PathVariable Long recruitId, @PathVariable Long memberId) {
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
