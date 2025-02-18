package com.tabletennis.infrastructure.db.room;

import com.tabletennis.core.common.PagedModel;
import com.tabletennis.core.room.Room;
import com.tabletennis.core.room.RoomReader;
import com.tabletennis.core.room.vo.RoomStatus;
import com.tabletennis.core.room.vo.RoomTypes;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DBRoomReader implements RoomReader {

    private final JpaUserRoomDao jpaUserRoomDao;
    private final JpaRoomDao jpaRoomDao;

    @Override
    public boolean isUserParticipate(long userId) {
        return jpaUserRoomDao.existsByUserId(userId);
    }

    @Override
    public Optional<Room> findRoomBy(long roomId) {
        return jpaRoomDao.findById(roomId).map(this::mapToEntity);
    }

    @Override
    public PagedModel<Room> findAllBy(int page, int size) {
        var pageable = PageRequest.of(page, size);
        Page<RoomRow> roomRowPage = jpaRoomDao.findAll(pageable);

        var rooms = roomRowPage.getContent()
                .stream()
                .map(this::mapToEntity)
                .toList();

        return PagedModel.<Room>builder()
                .totalElements(roomRowPage.getTotalElements())
                .totalPages(roomRowPage.getTotalPages())
                .data(rooms)
                .build();
    }

    private Room mapToEntity(RoomRow roomRow, List<UserRoomRow> userRoomRows) {
        return Room.builder()
                .id(roomRow.getId())
                .title(roomRow.getTitle())
                .host(roomRow.getHost())
                .roomType(roomRow.getRoomType())
                .status(roomRow.getStatus())
                .createdAt(roomRow.getCreatedAt())
                .updatedAt(roomRow.getUpdatedAt())
                .build();
    }

    private boolean generateIsFull(RoomRow roomRow, List<UserRoomRow> userRoomRows) {
        if (userRoomRows.isEmpty()) return false;

        RoomTypes roomType = roomRow.getRoomType();
        int userSizeOfRoom = userRoomRows.size();

        if (roomType == RoomTypes.SINGLE) {
            return userSizeOfRoom == 2;
        } else {
            return userSizeOfRoom == 4;
        }
    }
}
