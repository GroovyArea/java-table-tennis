package com.tabletennis.infrastructure.db.room;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaUserRoomDao extends JpaRepository<UserRoomRow, Long> {

    boolean existsByUserId(Long userId);
    List<UserRoomRow> findAllByRoomId(Long roomId);
    List<UserRoomRow> findAllByRoomIdIn(List<Long> roomIds);
    void deleteAllByRoomIdAndUserId(Long roomId, Long userId);
}
