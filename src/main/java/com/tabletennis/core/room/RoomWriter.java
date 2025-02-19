package com.tabletennis.core.room;

import java.util.List;

public interface RoomWriter {

    void deleteAll();
    Room createRoom(Room room);
    Room addUser(Room room);
    Room deleteUsers(Room room, List<Long> userIds);
}
