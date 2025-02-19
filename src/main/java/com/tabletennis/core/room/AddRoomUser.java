package com.tabletennis.core.room;

import com.tabletennis.core.room.vo.UserRoomTeams;
import com.tabletennis.core.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AddRoomUser {

    private final RoomWriter roomWriter;
    private final SelectRoomTeamSpecification selectRoomTeamSpecification;

    public Room addUser(Room room, User user) {
        UserRoomTeams userRoomTeam = selectRoomTeamSpecification.select(room);

        UserRoom newUserRoom = UserRoom.builder()
                .roomId(room.getId())
                .userId(user.getId())
                .userRoomTeams(userRoomTeam)
                .build();

        room.addUserRoom(newUserRoom);

        roomWriter.addUser(room);

        return room;
    }
}
