package com.tabletennis.application;

import com.tabletennis.api.common.ApiErrorException;
import com.tabletennis.api.common.MetaCode;
import com.tabletennis.api.response.RoomDetailInfoResponse;
import com.tabletennis.api.response.TotalRoomsResponse;
import com.tabletennis.core.room.Room;
import com.tabletennis.core.room.RoomReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoomInfoGetter {

    private final RoomReader roomReader;

    public TotalRoomsResponse getPaged(
            int size,
            int page
    ) {
        var pagedRooms = roomReader.findAllBy(page, size);

        var roomList = pagedRooms.data()
                .stream()
                .map(this::toListResponse)
                .toList();

        return TotalRoomsResponse.builder()
                .totalElements(pagedRooms.totalElements())
                .totalPages(pagedRooms.totalPages())
                .roomList(roomList)
                .build();
    }

    public RoomDetailInfoResponse getDetail(
            long roomId
    ) {
        return roomReader.findRoomBy(roomId)
                .map(this::toDetailResponse)
                .orElseThrow(() -> new ApiErrorException(MetaCode.CREATED));
    }


    private TotalRoomsResponse.Room toListResponse(Room room) {
        return TotalRoomsResponse.Room.builder()
                .id((int) room.getId())
                .title(room.getTitle())
                .hostId((int) room.getHost())
                .roomType(room.getRoomType())
                .status(room.getStatus())
                .build();
    }

    private RoomDetailInfoResponse toDetailResponse(Room room) {
        return RoomDetailInfoResponse.builder()
                .id((int) room.getId())
                .title(room.getTitle())
                .hostId((int) room.getHost())
                .roomType(room.getRoomType())
                .status(room.getStatus())
                .createdAt(room.getCreatedAt())
                .updatedAt(room.getUpdatedAt())
                .build();
    }
}
