package com.tabletennis.api.response;

import com.tabletennis.core.room.vo.RoomStatus;
import com.tabletennis.core.room.vo.RoomTypes;
import lombok.Builder;

import java.util.List;

@Builder
public record TotalRoomsResponse(
        long totalElements,
        int totalPages,
        List<Room> roomList
) {

    @Builder
    public record Room(
            int id,
            String title,
            int hostId,
            RoomTypes roomType,
            RoomStatus status
    ) {
    }
}
