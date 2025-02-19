package com.tabletennis.core.room;

import com.tabletennis.core.room.exception.RoomIsFullException;
import com.tabletennis.core.room.vo.UserRoomTeams;
import org.springframework.stereotype.Component;

@Component
public class SelectRoomTeamSpecification {

    public UserRoomTeams select(Room room) {
        boolean isRedTeamFull = room.isFull(UserRoomTeams.RED);
        boolean isBlueTeamFull = room.isFull(UserRoomTeams.BLUE);

        if (!isRedTeamFull) return UserRoomTeams.RED;
        if (!isBlueTeamFull) return UserRoomTeams.BLUE;
        throw new RoomIsFullException();
    }
}
