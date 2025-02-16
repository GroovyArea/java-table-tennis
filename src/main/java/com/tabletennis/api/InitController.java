package com.tabletennis.api;

import com.tabletennis.api.command.InitRequest;
import com.tabletennis.api.common.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * init api controller
 */
public class InitController {

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/init")
    public ApiResponse<Object> init(
            @RequestBody InitRequest request
    ) {
        // todo : usecase

        return ApiResponse.ok(null);
    }
}
