package com.tabletennis.core.room;

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
