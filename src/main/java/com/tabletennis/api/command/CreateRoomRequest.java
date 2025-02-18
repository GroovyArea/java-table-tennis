package com.tabletennis.api.command;

import com.tabletennis.core.room.vo.RoomTypes;

public record CreateRoomRequest(
        int userId,
        RoomTypes roomType,
        String title
) {
}
