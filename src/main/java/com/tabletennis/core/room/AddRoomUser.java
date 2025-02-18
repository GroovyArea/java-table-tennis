package com.tabletennis.core.room;

import com.tabletennis.core.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AddRoomUser {

    private final RoomWriter roomWriter;

    public Room addUser(Room room, User user) {

    }
}
