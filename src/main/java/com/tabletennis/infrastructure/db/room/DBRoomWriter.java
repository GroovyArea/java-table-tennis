package com.tabletennis.infrastructure.db.room;

import com.tabletennis.core.room.Room;
import com.tabletennis.core.room.RoomWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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

    private RoomRow mapToRow(Room room) {
        return RoomRow.builder()
                .title(room.getTitle())
                .host(room.getHost())
                .roomType(room.getRoomType())
                .status(room.getStatus())
                .build();
    }
}
