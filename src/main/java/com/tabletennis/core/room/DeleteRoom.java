package com.tabletennis.core.room;

import com.tabletennis.infrastructure.db.JpaRoomDao;
import com.tabletennis.infrastructure.db.JpaUserRoomDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class DeleteRoom {

    private final RoomWriter roomWriter;

    @Transactional
    public void deleteAll() {
        roomWriter.deleteAll();
    }
}
