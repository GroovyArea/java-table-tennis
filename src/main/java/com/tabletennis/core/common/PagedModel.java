package com.tabletennis.core.common;

import lombok.Builder;

import java.util.List;

@Builder
public record PagedModel<T extends PagedDomain>(
        long totalElements,
        int totalPages,
        List<T> data
) {
}

