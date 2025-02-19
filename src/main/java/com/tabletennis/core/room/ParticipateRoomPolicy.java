package com.tabletennis.core.room;

import com.tabletennis.core.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ParticipateRoomPolicy {

    private final RoomReader roomReader;

    public boolean isValid(Room room, User user) {
        return room.isWait() && user.isActive() && !roomReader.isUserParticipate(user.getId()) && !room.isFull();
    }
}
