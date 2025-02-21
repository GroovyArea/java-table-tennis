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
import java.util.List;

/**
 * room domain model
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Room implements PagedDomain {

    private static final int FULL_SIZE_OF_SINGLE = 2;
    private static final int FULL_SIZE_OF_DOUBLE = 4;

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
            return userSizeOfRoom == FULL_SIZE_OF_SINGLE;
        } else {
            return userSizeOfRoom == FULL_SIZE_OF_DOUBLE;
        }
    }

    public boolean isFull(UserRoomTeams userRoomTeams) {
        int userSizeOfRoom = userRooms.stream()
                .filter(userRoom -> userRoom.getUserRoomTeams() == userRoomTeams)
                .toList()
                .size();

        if (roomType == RoomTypes.SINGLE) {
            return userSizeOfRoom == FULL_SIZE_OF_SINGLE / 2;
        } else {
            return userSizeOfRoom == FULL_SIZE_OF_DOUBLE / 2;
        }
    }

    public int getCapacity() {
        if (this.roomType == RoomTypes.SINGLE) {
            return FULL_SIZE_OF_SINGLE;
        } else {
            return FULL_SIZE_OF_DOUBLE;
        }
    }

    public void addUserRoom(UserRoom newUserRoom) {
        this.userRooms.add(newUserRoom);
    }

    public boolean canLeave() {
        return this.status == RoomStatus.PROGRESS;
    }

    public boolean isUserJoined(long userId) {
        return this.userRooms.stream()
                .map(UserRoom::getUserId)
                .toList()
                .contains(userId);
    }

    public boolean isHost(long userId) {
        return this.host.equals(userId);
    }

    public void finish() {
        this.status = RoomStatus.FINISH;
    }

    public void start() {
        this.status = RoomStatus.PROGRESS;
    }

    public boolean canStart() {
        return this.status == RoomStatus.WAIT && this.isFull();
    }
}
