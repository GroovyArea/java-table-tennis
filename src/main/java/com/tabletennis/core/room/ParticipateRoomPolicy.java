package com.tabletennis.core.room;

import com.tabletennis.core.user.User;
import com.tabletennis.core.user.UserReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ParticipateRoomPolicy {

    private final RoomReader roomReader;

    public boolean isValid(Room room, User user) {
        return user.isActive() && !room.isWait() && roomReader.isUserParticipate(user.getId()) && room.isFull();
    }
}
