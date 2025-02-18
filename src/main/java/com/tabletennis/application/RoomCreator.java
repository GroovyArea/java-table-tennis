package com.tabletennis.application;

import com.tabletennis.api.command.CreateRoomRequest;
import com.tabletennis.api.common.ApiErrorException;
import com.tabletennis.api.common.MetaCode;
import com.tabletennis.core.room.CreateRoomPolicy;
import com.tabletennis.core.room.Room;
import com.tabletennis.core.room.RoomWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoomCreator {

    private final CreateRoomPolicy createRoomPolicy;
    private final RoomWriter roomWriter;

    public void create(CreateRoomRequest request) {
        if (!createRoomPolicy.isAble(request.userId())) throw new ApiErrorException(MetaCode.CREATED);

        roomWriter.createRoom(
                Room.of(request.title(), request.userId(), request.roomType())
        );
    }
}
