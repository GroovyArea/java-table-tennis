package com.tabletennis.core.room;

import com.tabletennis.core.room.vo.RoomStatus;
import com.tabletennis.core.room.vo.RoomTypes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * room domain model
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Room {

    private long id;
    private String title;
    private Long host;
    private RoomTypes roomType;
    private RoomStatus status;
}
