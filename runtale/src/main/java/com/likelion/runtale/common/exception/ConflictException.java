package com.likelion.runtale.common.exception;

import com.likelion.runtale.common.response.ErrorMessage;
import lombok.Getter;

@Getter
public class ConflictException extends BaseException{
    public ConflictException(ErrorMessage error) {
        super(error, "[ConflictException] " + error.getMessage());
    }
}
