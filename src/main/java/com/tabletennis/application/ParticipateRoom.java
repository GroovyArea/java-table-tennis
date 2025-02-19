package com.tabletennis.application;

import com.tabletennis.api.common.ApiErrorException;
import com.tabletennis.api.common.MetaCode;
import com.tabletennis.core.room.AddRoomUser;
import com.tabletennis.core.room.ParticipateRoomPolicy;
import com.tabletennis.core.room.Room;
import com.tabletennis.core.room.RoomReader;
import com.tabletennis.core.user.User;
import com.tabletennis.core.user.UserReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 방 참가 use case
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class ParticipateRoom {

    private final UserReader userReader;
    private final RoomReader roomReader;
    private final AddRoomUser addRoomUser;
    private final ParticipateRoomPolicy participateRoomPolicy;

    public void participate(long roomId, long userId) {
        User user = userReader.findBy(userId).orElseThrow(() -> new ApiErrorException(MetaCode.CREATED));
        Room room = roomReader.findRoomBy(roomId).orElseThrow(() -> new ApiErrorException(MetaCode.CREATED));

        if (!participateRoomPolicy.isValid(room, user)) {
            throw new ApiErrorException(MetaCode.CREATED);
        }

        try {
            addRoomUser.addUser(room, user);
        } catch (Exception ex) {
            log.error("Add room user failed", ex);
            throw new ApiErrorException(MetaCode.CREATED);
        }
    }
}
