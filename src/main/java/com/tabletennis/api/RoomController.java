package com.tabletennis.api;

import com.tabletennis.api.command.CreateRoomRequest;
import com.tabletennis.api.common.ApiResponse;
import com.tabletennis.application.RoomCreator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * for room api controller
 */
@RestController
@RequiredArgsConstructor
public class RoomController {

    private final RoomCreator roomCreator;

    /**
     * 방 생성
     */
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/room")
    public ApiResponse<Object> createRoom(
            @RequestBody CreateRoomRequest request
    ) {
        roomCreator.create(request);
        return ApiResponse.okWithNoContent();
    }
}
