package com.tabletennis.core.room;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StartRoomPolicy {

    public boolean isValid(Room room, long userId) {
        return room.isHost(userId) && room.canStart();
    }
}
