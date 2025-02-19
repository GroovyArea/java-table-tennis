package com.tabletennis.application;

import com.tabletennis.api.common.ApiErrorException;
import com.tabletennis.api.common.MetaCode;
import com.tabletennis.core.room.LeaveRoomPolicy;
import com.tabletennis.core.room.LeaveRoomUser;
import com.tabletennis.core.room.Room;
import com.tabletennis.core.room.RoomReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LeaveRoom {

    private final RoomReader roomReader;
    private final LeaveRoomPolicy leaveRoomPolicy;
    private final LeaveRoomUser leaveRoomUser;

    public void leave(long userId, long roomId) {
        Room room = roomReader.findRoomBy(roomId).orElseThrow(() -> new ApiErrorException(MetaCode.CREATED));

        if (!leaveRoomPolicy.isValid(userId, room)) throw new ApiErrorException(MetaCode.CREATED);

        leaveRoomUser.leave(room, userId);
    }
}
