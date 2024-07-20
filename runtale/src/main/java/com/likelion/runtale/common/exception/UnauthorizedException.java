package com.likelion.runtale.common.exception;

import com.likelion.runtale.common.response.ErrorMessage;
import lombok.Getter;

@Getter
public class UnauthorizedException extends BaseException{
    public UnauthorizedException(ErrorMessage error) {
        super(error, "[UnauthorizedException] " + error.getMessage());
    }
}
