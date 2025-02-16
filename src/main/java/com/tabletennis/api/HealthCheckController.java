package com.tabletennis.api;

import com.tabletennis.api.common.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * health check api controller
 */
@RestController
public class HealthCheckController {

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/health")
    public ApiResponse<Object> health() {
        return ApiResponse.ok(null);
    }
}
