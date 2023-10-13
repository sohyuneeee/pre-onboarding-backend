package com.wanted.preonboardingbackend.recruit.controller;

import com.wanted.preonboardingbackend.CustomException;
import com.wanted.preonboardingbackend.ErrorCode;
import com.wanted.preonboardingbackend.ResponseDto;
import com.wanted.preonboardingbackend.recruit.dto.RecruitDetailResponseDto;
import com.wanted.preonboardingbackend.recruit.dto.RecruitListResponseDto;
import com.wanted.preonboardingbackend.recruit.dto.RecruitRequestDto;
import com.wanted.preonboardingbackend.recruit.dto.RecruitResponseDto;
import com.wanted.preonboardingbackend.recruit.service.RecruitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class RecruitController {

    private final RecruitService recruitService;

    @PostMapping("/api/recruit")
    public ResponseDto<RecruitResponseDto> createRecruit(@RequestBody RecruitRequestDto recruitRequestDto) {
        RecruitResponseDto recruitResponseDto;
        try {
            recruitResponseDto = recruitService.createRecruit(recruitRequestDto);
        } catch (CustomException e) {
            log.error(e.getMessage());
            return new ResponseDto<>(null, e.getErrorCode());
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseDto<>(null, ErrorCode.INVALID_ERROR);
        }
        return new ResponseDto<>(recruitResponseDto);
    }

    @PutMapping("/api/recruit/{id}")
    public ResponseDto<RecruitResponseDto> updateRecruit(@PathVariable Long id, @RequestBody RecruitRequestDto recruitRequestDto) {
        RecruitResponseDto recruitResponseDto;
        try {
            recruitResponseDto = recruitService.updateRecruit(id, recruitRequestDto);
        } catch (CustomException e) {
            log.error(e.getMessage());
            return new ResponseDto<>(null, e.getErrorCode());
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseDto<>(null, ErrorCode.INVALID_ERROR);
        }
        return new ResponseDto<>(recruitResponseDto);
    }

    @DeleteMapping("/api/recruit/{id}")
    public ResponseDto<String> deleteRecruit(@PathVariable Long id) {
        try {
            recruitService.deleteRecruit(id);
        } catch (CustomException e) {
            log.error(e.getMessage());
            return new ResponseDto<>(null, e.getErrorCode());
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseDto<>(null, ErrorCode.INVALID_ERROR);
        }
        String data = "delete success";
        return new ResponseDto<>(data);
    }

    @GetMapping("/api/recruit")
    public ResponseDto<List<RecruitListResponseDto>> getAllRecruit() {
        List<RecruitListResponseDto> data;
        try {
            data = recruitService.getAllRecruit();
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseDto<>(null, ErrorCode.INVALID_ERROR);
        }
        return new ResponseDto<>(data);
    }

    @GetMapping("/api/recruit/{id}")
    public ResponseDto<RecruitDetailResponseDto> getRecruit(@PathVariable Long id) {
        RecruitDetailResponseDto recruitDetailResponseDto;
        try {
            recruitDetailResponseDto = recruitService.getRecruit(id);
        } catch (CustomException e) {
            log.error(e.getMessage());
            return new ResponseDto<>(null, e.getErrorCode());
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseDto<>(null, ErrorCode.INVALID_ERROR);
        }
        return new ResponseDto<>(recruitDetailResponseDto);
    }

    @GetMapping("api/recruit/search")
    public ResponseDto<List<RecruitListResponseDto>> searchRecruit(@RequestParam(value = "keyword") String keyword) {
        List<RecruitListResponseDto> data;
        try {
            data = recruitService.searchRecruit(keyword);
        } catch (CustomException e) {
            log.error(e.getMessage());
            return new ResponseDto<>(null, e.getErrorCode());
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseDto<>(null, ErrorCode.INVALID_ERROR);
        }
        return new ResponseDto<>(data);

    }
}
