package com.tabletennis.api.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {

    private Integer code;
    private String message;
    private T result;

    public static <T> ApiResponse<T> ok(T result) {
        return new ApiResponse<>(
                MetaCode.SUCCESS.getCode(),
                "API 요청이 성공했습니다.",
                result
        );
    }

    public static <T> ApiResponse<T> okWithNoContent() {
        return new ApiResponse<>(
                MetaCode.SUCCESS.getCode(),
                "API 요청이 성공했습니다.",
                null
        );
    }

    public static <T> ApiResponse<T> created() {
        return new ApiResponse<>(
                MetaCode.CREATED.getCode(),
                "불가능한 요청입니다.",
                null
        );
    }


    public static <T> ApiResponse<T> internalServerError() {
        return new ApiResponse<>(
                MetaCode.INTERNAL_SERVER_ERROR.getCode(),
                "에러가 발생했습니다.",
                null
        );
    }
}

