package com.tabletennis.application;

import com.tabletennis.api.common.ApiErrorException;
import com.tabletennis.api.common.MetaCode;
import com.tabletennis.core.room.*;
import com.tabletennis.core.room.vo.UserRoomTeams;
import com.tabletennis.core.user.User;
import com.tabletennis.core.user.UserReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * change team use case
 */
@Component
@RequiredArgsConstructor
public class ChangeTeam {

    private final RoomReader roomReader;
    private final UserReader userReader;
    private final ChangeRoomTeamPolicy changeRoomTeamPolicy;
    private final ChangeUserRoomTeam changeUserRoomTeam;

    public void change(long roomId, long userId) {
        Room room = roomReader.findRoomBy(roomId).orElseThrow(() -> new ApiErrorException(MetaCode.CREATED));
        User user = userReader.findBy(userId).orElseThrow(() -> new ApiErrorException(MetaCode.CREATED));
        var targetUserRoom = room.getUserRooms()
                .stream()
                .filter(userRoom -> userRoom.getUserId() == user.getId())
                .findAny()
                .orElseThrow(() -> new ApiErrorException(MetaCode.CREATED));

        UserRoomTeams targetTeam;
        if (targetUserRoom.getUserRoomTeams() == UserRoomTeams.RED) {
            targetTeam = UserRoomTeams.BLUE;
        } else {
            targetTeam = UserRoomTeams.RED;
        }

        if (!changeRoomTeamPolicy.isValid(room, user, targetTeam)) {
            throw new ApiErrorException(MetaCode.CREATED);
        }

        changeUserRoomTeam.change(room, targetUserRoom, targetTeam);
    }
}
