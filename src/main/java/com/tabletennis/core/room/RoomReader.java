package com.tabletennis.core.room;

import com.tabletennis.infrastructure.db.room.UserRoomRow;

import java.util.Optional;

public interface RoomReader {

    Optional<UserRoomRow> findBy(long userId);
}
