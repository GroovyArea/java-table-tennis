package com.tabletennis.core.room;

import com.tabletennis.core.common.PagedModel;
import com.tabletennis.infrastructure.db.room.UserRoomRow;

import java.util.Optional;

public interface RoomReader {

    Optional<UserRoomRow> findBy(long userId);
    PagedModel<Room> findAllBy(int page, int size);
}
