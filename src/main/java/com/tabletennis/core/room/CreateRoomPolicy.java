package com.tabletennis.core.room;

import com.tabletennis.core.user.RoomUsers;
import com.tabletennis.core.user.User;
import com.tabletennis.core.user.UserGetter;
import com.tabletennis.core.user.vo.UserStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateRoomPolicy {

    private final UserGetter userGetter;
    private final RoomUsers roomUsers;

    public boolean isAble(long userId) {
        User user = userGetter.get(userId);
        return isUserActive(user) && hasNotUserRoom(user);
    }

    private boolean isUserActive(User user) {
        return user.getStatus() == UserStatus.ACTIVE;
    }

    private boolean hasNotUserRoom(User user) {
        return !roomUsers.hasRoom(user.getId());
    }
}
