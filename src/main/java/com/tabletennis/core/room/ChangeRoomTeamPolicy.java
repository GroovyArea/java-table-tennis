package com.tabletennis.core.room;

import com.tabletennis.core.room.vo.UserRoomTeams;
import com.tabletennis.core.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChangeRoomTeamPolicy {

    private final CapacityRoomPolicy capacityRoomPolicy;

    public boolean isValid(Room room, User user, UserRoomTeams userRoomTeams) {
        return capacityRoomPolicy.isValid(room, userRoomTeams) && room.isUserJoined(user.getId()) && room.isWait();
    }
}
