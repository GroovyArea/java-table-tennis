package com.tabletennis.infrastructure.db.room;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRoomDao extends JpaRepository<UserRoomRow, Long> {
}
