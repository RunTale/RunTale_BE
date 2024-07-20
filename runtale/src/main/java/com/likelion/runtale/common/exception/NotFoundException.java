package com.likelion.runtale.common.exception;

import com.likelion.runtale.common.response.ErrorMessage;
import lombok.Getter;


@Getter
public class NotFoundException extends BaseException {
    public NotFoundException(ErrorMessage error) {
        super(error, "[NotFoundException] "+ error.getMessage());
    }
}
