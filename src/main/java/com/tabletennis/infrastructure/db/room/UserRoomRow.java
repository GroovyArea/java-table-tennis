package com.tabletennis.infrastructure.db.room;

import com.tabletennis.core.room.vo.UserRoomTeams;
import com.tabletennis.infrastructure.db.EntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "user_rooms")
public class UserRoomRow extends EntityBase {

    private long roomId;

    private long userId;

    @Enumerated(EnumType.STRING)
    private UserRoomTeams team;

    public static UserRoomRow ofFirstUserRoom(long roomId, long userId) {
        return UserRoomRow.builder()
                .roomId(roomId)
                .userId(userId)
                .team(UserRoomTeams.RED)
                .build();
    }
}


