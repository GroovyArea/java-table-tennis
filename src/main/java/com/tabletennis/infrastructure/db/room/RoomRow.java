package com.tabletennis.infrastructure.db.room;

import com.tabletennis.core.room.vo.RoomStatus;
import com.tabletennis.core.room.vo.RoomTypes;
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
@Table(name = "rooms")
public class RoomRow extends EntityBase {

    private String title;

    private Long host;

    @Enumerated(EnumType.STRING)
    private RoomTypes roomType;

    @Enumerated(EnumType.STRING)
    private RoomStatus status;
}

