package com.tabletennis.core.room;

import com.tabletennis.core.room.vo.UserRoomTeams;
import org.springframework.stereotype.Component;

@Component
public class CapacityRoomPolicy {

    public boolean isValid(Room room, UserRoomTeams targetTeam) {
        var totalUserRooms = room.getUserRooms();
        int roomCapacity = room.getCapacity();

        long targetTeamSize = totalUserRooms.stream()
                .filter(userRoom -> userRoom.getUserRoomTeams() == targetTeam)
                .count();

        return (roomCapacity / 2) != targetTeamSize;
    }
}
