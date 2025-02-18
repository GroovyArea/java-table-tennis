package com.tabletennis.infrastructure.db.room;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaRoomDao extends JpaRepository<RoomRow, Long> {
}
