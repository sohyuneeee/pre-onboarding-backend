package com.wanted.preonboardingbackend;

import lombok.Getter;

@Getter
public enum ErrorCode {
    ENTITY_NOT_FOUND("NOT_FOUND", "데이터가 존재하지 않습니다."),
    POSTING_ID_NOT_FOUND("POSTING_ID_NOT_FOUND", "게시글이 존재하지 않습니다."),
    INVALID_ERROR("INVALID_ERROR", "에러 발생"),
    DUPLICATION_NICKNAME("DUPLICATION_NICKNAME", "중복된 아이디입니다.");

    private final String code;
    private final String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
