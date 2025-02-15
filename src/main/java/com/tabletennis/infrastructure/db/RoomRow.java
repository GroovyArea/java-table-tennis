package com.tabletennis.infrastructure.db;

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
public class RoomRow extends EntityBase{

    private String title;

    private Long host;

    @Enumerated(EnumType.STRING)
    private RoomTypes roomType;

    @Enumerated(EnumType.STRING)
    private RoomStatus status;
}

enum RoomTypes {

    SINGLE,
    DOUBLE,
}

enum RoomStatus {

    WAIT,
    PROGRESS,
    FINISH;
}
