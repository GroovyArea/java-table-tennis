package com.tabletennis.api;

import com.tabletennis.api.command.InitRequest;
import com.tabletennis.api.common.ApiResponse;
import com.tabletennis.api.usecase.InitExecutor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * init api controller
 */
@RestController
@RequiredArgsConstructor
public class InitController {

    private final InitExecutor initExecutor;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/init")
    public ApiResponse<Object> init(
            @RequestBody InitRequest request
    ) {
        initExecutor.execute(request.seed(), request.quantity());
        return ApiResponse.ok(null);
    }
}
