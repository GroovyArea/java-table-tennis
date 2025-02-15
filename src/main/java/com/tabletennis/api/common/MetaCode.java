package com.tabletennis.api.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MetaCode {

    SUCCESS(200),
    CREATED(201),
    INTERNAL_SERVER_ERROR(500);

    private final int code;
}
