package com.tabletennis.core.user;

import com.tabletennis.core.room.RoomReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoomUsers {

    private final RoomReader roomReader;

    public boolean hasRoom(long userId) {
        boolean isPresent = roomReader.findBy(userId).isPresent();
        return isPresent;
    }
}
