package com.tabletennis.api;

import com.tabletennis.api.command.AttentionRoomRequest;
import com.tabletennis.api.command.CreateRoomRequest;
import com.tabletennis.api.command.RoomOutRequest;
import com.tabletennis.api.common.ApiResponse;
import com.tabletennis.api.response.RoomDetailInfoResponse;
import com.tabletennis.api.response.TotalRoomsResponse;
import com.tabletennis.application.LeaveRoom;
import com.tabletennis.application.ParticipateRoom;
import com.tabletennis.application.RoomCreator;
import com.tabletennis.application.RoomInfoGetter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * for room api controller
 */
@RestController
@RequiredArgsConstructor
public class RoomController {

    private final RoomCreator roomCreator;
    private final RoomInfoGetter roomInfoGetter;
    private final ParticipateRoom participateRoom;
    private final LeaveRoom leaveRoom;

    /**
     * 방 생성 API
     */
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/room")
    public ApiResponse<Void> createRoom(
            @RequestBody CreateRoomRequest request
    ) {
        roomCreator.create(request);
        return ApiResponse.okWithNoContent();
    }

    /**
     * 방 목록 조회 API
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/room")
    public ApiResponse<TotalRoomsResponse> getRooms(
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "0") int page
    ) {
        var data = roomInfoGetter.getPaged(size, page);
        return ApiResponse.ok(data);
    }

    /**
     * 방 상세 조회 API
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/room/{roomId}")
    public ApiResponse<RoomDetailInfoResponse> getRoomDetailInfo(
            @PathVariable long roomId
    ) {
        var data = roomInfoGetter.getDetail(roomId);
        return ApiResponse.ok(data);
    }

    /**
     * 방 참가 API
     */
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/room/attention/{roomId}")
    public ApiResponse<Void> addAttention(
            @PathVariable long roomId,
            @RequestBody AttentionRoomRequest request
    ) {
        participateRoom.participate(roomId, request.userId());
        return ApiResponse.okWithNoContent();
    }

    /**
     * 방 나가기 API
     */
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/room/out/{roomId}")
    public ApiResponse<Void> removeAttention(
            @PathVariable long roomId,
            @RequestBody RoomOutRequest request
    ) {
        leaveRoom.leave(request.userId(), roomId);
        return ApiResponse.okWithNoContent();
    }
}
