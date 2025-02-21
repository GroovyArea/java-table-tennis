package com.tabletennis.api;

import com.tabletennis.api.command.ChangeTeamRequest;
import com.tabletennis.api.common.ApiResponse;
import com.tabletennis.application.ChangeTeam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * for team api controller
 */
@RestController
@RequiredArgsConstructor
public class TeamController {

    private final ChangeTeam changeTeam;

    /**
     * 팀 변경 API
     */
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/team/{roomId}")
    public ApiResponse<Void> changeTeam(
            @PathVariable long roomId,
            @RequestBody ChangeTeamRequest request
    ) {
        changeTeam.change(roomId, request.userId());
        return ApiResponse.okWithNoContent();
    }
}
