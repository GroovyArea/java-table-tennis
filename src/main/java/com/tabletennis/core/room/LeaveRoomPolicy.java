package com.tabletennis.core.room;

import org.springframework.stereotype.Component;

@Component
public class LeaveRoomPolicy {

    public boolean isValid(long userId, Room room) {
        return room.canLeave() && room.isUserJoined(userId);
    }
}
