package com.tabletennis.api.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalControllerAdvice {

    @ExceptionHandler(ApiErrorException.class)
    public ApiResponse<Object> handleApiError(ApiErrorException ex) {
        log.error(ex.getMessage(), ex);

        if (ex.getMetacode() == MetaCode.CREATED) {
            return ApiResponse.created();
        } else {
            return ApiResponse.internalServerError();
        }
    }

    @ExceptionHandler(Exception.class)
    public ApiResponse<Object> handleUnknownError(ApiErrorException ex) {
        log.error(ex.getMessage(), ex);
        return ApiResponse.internalServerError();
    }
}
