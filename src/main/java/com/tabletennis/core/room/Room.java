package com.tabletennis.core.room;

import com.tabletennis.core.common.PagedDomain;
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
public class Room implements PagedDomain {

    private long id;
    private String title;
    private long host;
    private RoomTypes roomType;
    private RoomStatus status;

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
}
