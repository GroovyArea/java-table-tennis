package com.tabletennis.api;

import com.tabletennis.api.common.ApiResponse;
import com.tabletennis.api.response.TotalUsersResponse;
import com.tabletennis.application.UserGetter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * for user api controller
 */
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserGetter userGetter;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/user")
    public ApiResponse<TotalUsersResponse> getUsers(
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "0") int page
    ) {
        var data = userGetter.getPaged(size, page);
        return ApiResponse.ok(data);
    }
}
