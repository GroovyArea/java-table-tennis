package com.tabletennis.infrastructure.db;

import com.tabletennis.core.room.RoomWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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
}
