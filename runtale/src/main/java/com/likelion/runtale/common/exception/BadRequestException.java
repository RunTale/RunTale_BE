package com.likelion.runtale.common.exception;

import com.likelion.runtale.common.response.ErrorMessage;
import lombok.Getter;

@Getter
public class BadRequestException extends BaseException{
    public BadRequestException(ErrorMessage error) {
        super(error, "[BadRequestException] " + error.getMessage());
    }
}
