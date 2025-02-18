package com.tabletennis.infrastructure.db;

import com.tabletennis.core.room.vo.UserRoomTeams;
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

    private Long roomId;

    private Long userId;

    @Enumerated(EnumType.STRING)
    private UserRoomTeams team;
}

