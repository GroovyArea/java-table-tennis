package com.tabletennis.infrastructure.db.room;

import com.tabletennis.core.room.Room;
import com.tabletennis.core.room.RoomWriter;
import com.tabletennis.core.room.UserRoom;
import com.tabletennis.core.room.vo.UserRoomTeams;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DBRoomWriter implements RoomWriter {

    private final JpaRoomDao jpaRoomDao;
    private final JpaUserRoomDao jpaUserRoomDao;

    @Override
    public void deleteAll() {
        jpaRoomDao.deleteAll();
        jpaUserRoomDao.deleteAll();
    }

    @Override
    @Transactional
    public Room createRoom(Room room) {
        RoomRow savedRoomRow = jpaRoomDao.save(mapToRow(room));
        UserRoomRow userRoomRow = UserRoomRow.ofFirstUserRoom(savedRoomRow.getId(), room.getHost());
        jpaUserRoomDao.save(userRoomRow);
        return room;
    }

    @Override
    public Room addUser(Room room) {
        List<UserRoomRow> userRoomRows = room.getUserRooms()
                .stream()
                .map(this::mapToRow)
                .toList();
        jpaUserRoomDao.saveAll(userRoomRows);

        return room;
    }

    private RoomRow mapToRow(Room room) {
        RoomRow roomRow = RoomRow.builder()
                .title(room.getTitle())
                .host(room.getHost())
                .roomType(room.getRoomType())
                .status(room.getStatus())
                .build();
        roomRow.setId(room.getId());

        return roomRow;
    }

    private UserRoomRow mapToRow(UserRoom userRoom) {
        UserRoomRow userRoomRow = UserRoomRow.builder()
                .userId(userRoom.getUserId())
                .roomId(userRoom.getRoomId())
                .team(userRoom.getUserRoomTeams())
                .build();
        userRoomRow.setId(userRoom.getId());

        return userRoomRow;
    }
}
