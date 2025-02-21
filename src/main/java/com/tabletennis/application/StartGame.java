package com.tabletennis.application;

import com.tabletennis.api.common.ApiErrorException;
import com.tabletennis.api.common.MetaCode;
import com.tabletennis.core.StartRoomPolicy;
import com.tabletennis.core.room.Room;
import com.tabletennis.core.room.RoomReader;
import com.tabletennis.core.room.RoomWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StartGame {

    private final RoomReader roomReader;
    private final RoomWriter roomWriter;
    private final StartRoomPolicy startRoomPolicy;
    private final GameFinishScheduler gameFinishScheduler;

    public void start(long roomId, long userId) {
        Room room = roomReader.findRoomBy(roomId).orElseThrow(() -> new ApiErrorException(MetaCode.CREATED));

        if (!startRoomPolicy.isValid(room, userId)) throw new ApiErrorException(MetaCode.CREATED);

        room.start();
        roomWriter.saveRoom(room);

        gameFinishScheduler.scheduleFinish(roomId);
    }
}
