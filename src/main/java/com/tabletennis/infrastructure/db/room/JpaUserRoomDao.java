package com.tabletennis.infrastructure.db.room;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaUserRoomDao extends JpaRepository<UserRoomRow, Long> {

    boolean existsByUserId(Long userId);
    Optional<UserRoomRow> findByRoomId(Long roomId);
}
