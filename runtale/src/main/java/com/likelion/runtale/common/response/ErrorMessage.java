package com.likelion.runtale.common.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ErrorMessage {
    USER_LOGIN_ID_NOT_EXIST(NOT_FOUND, "존재하지 않는 아이디"),
    USER_PASSWORD_NOT_EXIST(NOT_FOUND, "존재하지 않는 비밀번호"),
    USER_NOT_EXIST(NOT_FOUND, "존재하지 않는 회원"),
    USER_LOGIN_ID_VALIDATE(BAD_REQUEST, "아이디 중복"),
    USER_NOT_LOGGED_IN(HttpStatus.UNAUTHORIZED, "로그인이 필요합니다."),
    USER_NOT_AUTHORIZED(HttpStatus.FORBIDDEN, "권한이 없습니다."),
    USER_LOGIN_FAILED(HttpStatus.UNAUTHORIZED, "로그인 실패"),

    ;

    private final HttpStatus httpStatus;
    private final String message;

    public int getHttpStatusValue(){
        return httpStatus.value();
    }

}
