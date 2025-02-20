package com.tabletennis.core.room;

import com.tabletennis.application.ChangeTeam;
import com.tabletennis.core.room.vo.UserRoomTeams;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRoom {

    private Long id;
    private long roomId;
    private long userId;
    private UserRoomTeams userRoomTeams;

    public void changeTeam(UserRoomTeams targetTeam) {
        this.userRoomTeams = targetTeam;
    }
}
