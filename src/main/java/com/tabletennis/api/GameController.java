package com.tabletennis.api;

import com.tabletennis.api.command.StartGameRequest;
import com.tabletennis.api.common.ApiResponse;
import com.tabletennis.application.StartGame;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * for game api controller
 */
@RestController
@RequiredArgsConstructor
public class GameController {

    private final StartGame startGame;

    /**
     * 게임 시작 API
     */
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/room/start/{roomId}")
    public ApiResponse<Void> startRoom(
            @PathVariable long roomId,
            @RequestBody StartGameRequest request
    ) {
        startGame.start(roomId, request.userId());
        return ApiResponse.okWithNoContent();
    }
}
