package com.wanted.preonboardingbackend;

import lombok.Getter;

@Getter
public enum ErrorCode {
    ENTITY_NOT_FOUND("NOT_FOUND", "데이터가 존재하지 않습니다."),
    COMPANY_NOT_FOUND("COMPANY_NOT_FOUND", "등록되지 않은 기업입니다."),
    RECRUITMENT_NOT_FOUND("POSTING_NOT_FOUND", "채용공고가 존재하지 않습니다."),
    MEMBER_NOT_FOUND("MEMBER_NOT_FOUND", "사용자를 찾을 수 없습니다."),
    DUPLICATION_NICKNAME("DUPLICATION_NICKNAME", "중복된 아이디입니다."),
    DUPLICATION_COMPANY("DUPLICATION_COMPANY", "이미 존재하는 기업입니다."),
    INVALID_ERROR("INVALID_ERROR", "에러 발생");

    private final String code;
    private final String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
