package com.tabletennis.core.room;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LeaveRoomPolicy {

    public boolean isValid(long userId, Room room) {
        return room.canLeave() && room.isUserJoined(userId);
    }
}
