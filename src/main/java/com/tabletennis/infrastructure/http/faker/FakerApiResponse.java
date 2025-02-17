package com.tabletennis.infrastructure.http.faker;

import java.util.List;

public record FakerApiResponse(
        String status,
        int code,
        String locale,
        String seed,
        int total,
        List<FakerUser> data
) {}

