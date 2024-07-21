package com.likelion.runtale.common.exception;

import com.likelion.runtale.common.ApiResponse;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class ControllerExceptionAdvice {

    // 400 BAD REQUEST
    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<ApiResponse> BadRequestException(BaseException exception) {
        return ResponseEntity.status(BAD_REQUEST).body(ApiResponse.error(exception.getError(), exception.getMessage()));
    }

    // 401 UNAUTHORIZED
    @ExceptionHandler({UnauthorizedException.class})
    public ResponseEntity<ApiResponse> UnauthorizedException(BaseException exception) {
        return ResponseEntity.status(UNAUTHORIZED).body(ApiResponse.error(exception.getError(), exception.getMessage()));
    }

    // 403 FORBIDDEN
    @ExceptionHandler({ForbiddenException.class})
    public ResponseEntity<ApiResponse> ForbiddenException(BaseException exception) {
        return ResponseEntity.status(FORBIDDEN).body(ApiResponse.error(exception.getError(), exception.getMessage()));
    }

    // 404 NOT FOUND
    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<ApiResponse> NotFoundException(BaseException exception) {
        return ResponseEntity.status(NOT_FOUND).body(ApiResponse.error(exception.getError(), exception.getMessage()));
    }

    // 409 CONFLICT
    @ExceptionHandler({ConflictException.class})
    public ResponseEntity<ApiResponse> ConflictException(BaseException exception) {
        return ResponseEntity.status(CONFLICT).body(ApiResponse.error(exception.getError(), exception.getMessage()));
    }

    // 500 INTERNAL SERVER ERROR


}
