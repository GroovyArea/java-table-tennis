package com.tabletennis.core.room;

public interface RoomWriter {

    void deleteAll();
    Room createRoom(Room room);
    Room addUser(Room room);
}
