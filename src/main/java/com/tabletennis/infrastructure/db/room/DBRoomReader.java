package com.tabletennis.infrastructure.db.room;

import com.tabletennis.core.common.PagedModel;
import com.tabletennis.core.room.Room;
import com.tabletennis.core.room.RoomReader;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DBRoomReader implements RoomReader {

    private final JpaUserRoomDao jpaUserRoomDao;
    private final JpaRoomDao jpaRoomDao;

    @Override
    public Optional<UserRoomRow> findBy(long userId) {
        return jpaUserRoomDao.findById(userId);
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

    private Room mapToEntity(RoomRow roomRow) {
        return Room.builder()
                .id(roomRow.getId())
                .title(roomRow.getTitle())
                .host(roomRow.getHost())
                .roomType(roomRow.getRoomType())
                .status(roomRow.getStatus())
                .build();
    }
}
