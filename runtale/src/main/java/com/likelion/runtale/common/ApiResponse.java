package com.likelion.runtale.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.likelion.runtale.common.response.ErrorMessage;
import com.likelion.runtale.common.response.SuccessMessage;
import com.likelion.runtale.domain.running.dto.RunningResponse;
import jakarta.annotation.Nullable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiResponse<T> {

    private final int status;
    private final String message;
    @JsonInclude(NON_NULL)
    private T data;

    private ApiResponse(){
        throw new IllegalStateException();
    }

    public static <T> ApiResponse<T> success(SuccessMessage success) {
        return new ApiResponse<>(success.getHttpStatusValue(), success.getMessage());
    }

    public static <T> ApiResponse<T> success(SuccessMessage success, T data){
        return new ApiResponse<>(success.getHttpStatusValue(), success.getMessage(), data);
    }

    public static <T> ApiResponse<T> error(ErrorMessage error){
        return new ApiResponse<>(error.getHttpStatusValue(), error.getMessage());
    }

    public static <T> ApiResponse<T> error(ErrorMessage error, @Nullable String message){
        return new ApiResponse<>(error.getHttpStatusValue(), message);
    }

    public static <T> ApiResponse<T> error(ErrorMessage error, @Nullable String message, @Nullable T data){
        return new ApiResponse<>(error.getHttpStatusValue(), message, data);
    }

    public static ApiResponse<List<RunningResponse>> failure(ErrorMessage errorMessage) {
        return new ApiResponse<>(errorMessage.getHttpStatusValue(), errorMessage.getMessage());
    }
}
