package com.tabletennis.infrastructure.db.room;

import com.tabletennis.core.common.PagedModel;
import com.tabletennis.core.room.Room;
import com.tabletennis.core.room.RoomReader;
import com.tabletennis.core.room.UserRoom;
import com.tabletennis.core.room.vo.RoomTypes;
import com.tabletennis.infrastructure.db.EntityBase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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
        List<UserRoomRow> userRoomRows = jpaUserRoomDao.findAllByRoomId(roomId);
        return jpaRoomDao.findById(roomId).map(roomRow ->
                mapToEntity(roomRow, userRoomRows)
        );
    }

    @Override
    public PagedModel<Room> findAllBy(int page, int size) {
        var pageable = PageRequest.of(page, size);
        Page<RoomRow> roomRowPage = jpaRoomDao.findAll(pageable);
        List<RoomRow> roomRows = roomRowPage.getContent();
        var roomIds = roomRows.stream().map(EntityBase::getId).toList();
        List<UserRoomRow> userRoomRows = jpaUserRoomDao.findAllByRoomIdIn(roomIds);

        var rooms = roomRowPage.getContent()
                .stream()
                .map(
                        roomRow -> {
                            var targetUserRoomRows = userRoomRows.stream()
                                    .filter(userRoomRow -> userRoomRow.getRoomId() == roomRow.getId())
                                    .toList();

                            return mapToEntity(roomRow, targetUserRoomRows);
                        }
                )
                .toList();

        return PagedModel.<Room>builder()
                .totalElements(roomRowPage.getTotalElements())
                .totalPages(roomRowPage.getTotalPages())
                .data(rooms)
                .build();
    }

    private Room mapToEntity(RoomRow roomRow, List<UserRoomRow> userRoomRows) {
        var userRooms = userRoomRows.stream()
                .map(this::mapToEntity)
                .toList();

        List<UserRoom> savedUserRooms = new ArrayList<>(userRooms);

        Room room = Room.builder()
                .id(roomRow.getId())
                .title(roomRow.getTitle())
                .host(roomRow.getHost())
                .roomType(roomRow.getRoomType())
                .status(roomRow.getStatus())
                .userRooms(savedUserRooms)
                .createdAt(roomRow.getCreatedAt())
                .updatedAt(roomRow.getUpdatedAt())
                .build();

        return room;
    }

    private UserRoom mapToEntity(UserRoomRow userRoomRow) {
        return UserRoom.builder()
                .id(userRoomRow.getId())
                .userId(userRoomRow.getUserId())
                .roomId(userRoomRow.getRoomId())
                .userRoomTeams(userRoomRow.getTeam())
                .build();
    }
}
