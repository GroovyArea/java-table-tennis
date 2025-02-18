package com.tabletennis.api.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tabletennis.core.room.vo.RoomStatus;
import com.tabletennis.core.room.vo.RoomTypes;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record RoomDetailInfoResponse(
        int id,
        String title,
        int hostId,
        RoomTypes roomType,
        RoomStatus status,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime createdAt,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime updatedAt
) {
}
