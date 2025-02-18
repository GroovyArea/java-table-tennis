package com.tabletennis.infrastructure.db.room;

import com.tabletennis.core.room.RoomReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DBRoomReader implements RoomReader {

    private final JpaUserRoomDao jpaUserRoomDao;

    @Override
    public Optional<UserRoomRow> findBy(long userId) {
        return jpaUserRoomDao.findById(userId);
    }
}
