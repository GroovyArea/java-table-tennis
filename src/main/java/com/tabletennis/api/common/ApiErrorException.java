package com.tabletennis.api.common;

import lombok.Getter;

@Getter
public class ApiErrorException extends RuntimeException {

    private final MetaCode metacode;

    public ApiErrorException(MetaCode metacode) {
        this.metacode = metacode;
    }

    public ApiErrorException(String message, MetaCode metacode) {
        super(message);
        this.metacode = metacode;
    }

    public ApiErrorException(String message, Throwable cause, MetaCode metacode) {
        super(message, cause);
        this.metacode = metacode;
    }
}
