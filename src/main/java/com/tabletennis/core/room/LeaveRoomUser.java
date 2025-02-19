package com.tabletennis.core.room;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LeaveRoomUser {

    private final RoomWriter roomWriter;

    public Room leave(Room room, long userId) {
        List<Long> leaveUserIds ;

        if (room.isHost(userId)) {
            leaveUserIds = room.getUserRooms().stream().map(UserRoom::getUserId).toList();
        } else {
            leaveUserIds = List.of(userId);
        }

        room.finish();
        roomWriter.deleteUsers(room, leaveUserIds);

        return room;
    }
}
