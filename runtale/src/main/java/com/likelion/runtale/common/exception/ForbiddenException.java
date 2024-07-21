package com.likelion.runtale.common.exception;

import com.likelion.runtale.common.response.ErrorMessage;
import lombok.Getter;

@Getter
public class ForbiddenException extends BaseException{
    public ForbiddenException(ErrorMessage error) {
        super(error, "[ForbiddenException] " + error.getMessage());
    }
}
