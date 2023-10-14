package com.wanted.preonboardingbackend.controller;

import com.wanted.preonboardingbackend.exception.CustomException;
import com.wanted.preonboardingbackend.exception.ErrorCode;
import com.wanted.preonboardingbackend.dto.responseDto.ResponseDto;
import com.wanted.preonboardingbackend.dto.responseDto.RecruitDetailResponseDto;
import com.wanted.preonboardingbackend.dto.responseDto.RecruitListResponseDto;
import com.wanted.preonboardingbackend.dto.requestDto.RecruitRequestDto;
import com.wanted.preonboardingbackend.dto.responseDto.RecruitResponseDto;
import com.wanted.preonboardingbackend.service.RecruitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class RecruitController {

    private final RecruitService recruitService;

    @PostMapping("/api/recruit")
    @ResponseStatus(HttpStatus.CREATED)
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
    @ResponseStatus(HttpStatus.CREATED)
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
    @ResponseStatus(HttpStatus.OK)
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
    @ResponseStatus(HttpStatus.OK)
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
    @ResponseStatus(HttpStatus.OK)
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
    @ResponseStatus(HttpStatus.OK)
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
