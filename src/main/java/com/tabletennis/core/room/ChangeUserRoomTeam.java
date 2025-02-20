package com.tabletennis.core.room;

import com.tabletennis.core.room.vo.UserRoomTeams;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class ChangeUserRoomTeam {

    private final RoomWriter roomWriter;

    public void change(Room room, UserRoom targetUserRoom, UserRoomTeams targetTeam) {
        room.getUserRooms()
                .forEach(userRoom -> {
                    if (Objects.equals(userRoom.getId(), targetUserRoom.getId())) {
                        userRoom.changeTeam(targetTeam);
                    }
                });

        roomWriter.saveRoom(room);
    }
}
