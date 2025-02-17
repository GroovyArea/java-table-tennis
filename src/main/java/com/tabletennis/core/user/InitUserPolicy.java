package com.tabletennis.core.user;

import com.tabletennis.infrastructure.db.UserRow;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class InitUserPolicy {

    private static final int ACTIVE_STATUS_ID = 30;
    private static final int WAIT_STATUS_ID = 60;

    private final UserWriter userWriter;

    public void init(List<User> users) {
        List<UserRow> userRows = users.stream()
                .map(user ->
                        UserRow.builder()
                                .fakerId(user.fakerId())
                                .name(user.userName())
                                .email(user.email())
                                .status(generateStatus(user.fakerId))
                                .build()
                ).toList();

        userWriter.saveAll(userRows);
    }

    private UserStatus generateStatus(long fakerId) {
        UserStatus status;

        if (fakerId <= ACTIVE_STATUS_ID) {
            status = UserStatus.ACTIVE;
        } else if (fakerId <= WAIT_STATUS_ID) {
            status = UserStatus.WAIT;
        } else {
            status = UserStatus.NON_ACTIVE;
        }

        return status;
    }

    public static record User(
            long fakerId,
            String userName,
            String email
    ) {
    }
}
