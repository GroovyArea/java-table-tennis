package com.tabletennis.core.room;

import com.tabletennis.core.common.PagedDomain;
import com.tabletennis.core.room.vo.RoomStatus;
import com.tabletennis.core.room.vo.RoomTypes;
import com.tabletennis.core.room.vo.UserRoomTeams;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * room domain model
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Room implements PagedDomain {

    private Long id;
    private String title;
    private Long host;
    private RoomTypes roomType;
    private RoomStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<UserRoom> userRooms;

    public static Room of(
            String title, long host, RoomTypes roomType
    ) {
        return Room.builder()
                .title(title)
                .host(host)
                .roomType(roomType)
                .status(RoomStatus.WAIT)
                .build();
    }

    public boolean isWait() {
        return status == RoomStatus.WAIT;
    }

    public boolean isFull() {
        if (this.userRooms.isEmpty()) return false;

        int userSizeOfRoom = userRooms.size();

        if (roomType == RoomTypes.SINGLE) {
            return userSizeOfRoom == 2;
        } else {
            return userSizeOfRoom == 4;
        }
    }

    public boolean isFull(UserRoomTeams userRoomTeams) {
        int userSizeOfRoom = userRooms.stream()
                .filter(userRoom -> userRoom.getUserRoomTeams() == userRoomTeams)
                .toList()
                .size();

        if (roomType == RoomTypes.SINGLE) {
            return userSizeOfRoom == 1;
        } else {
            return userSizeOfRoom == 2;
        }
    }

    public void addUserRoom(UserRoom newUserRoom) {
        this.userRooms.add(newUserRoom);
    }
}
