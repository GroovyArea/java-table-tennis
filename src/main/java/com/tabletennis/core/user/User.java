package com.tabletennis.core.user;

import com.tabletennis.core.common.PagedDomain;
import com.tabletennis.core.user.vo.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * User Domain Model
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements PagedDomain {

    private static final int ACTIVE_STATUS_ID = 30;
    private static final int WAIT_STATUS_ID = 60;

    private long id;
    private long fakerId;
    private String name;
    private String email;
    private UserStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static User of(long fakerId, String name, String email) {
        return User.builder()
                .fakerId(fakerId)
                .name(name)
                .email(email)
                .build();
    }

    public boolean isActive() {
        return status == UserStatus.ACTIVE;
    }

    public User ofWithStatus() {
        if (fakerId <= ACTIVE_STATUS_ID) {
            this.status = UserStatus.ACTIVE;
        } else if (fakerId <= WAIT_STATUS_ID) {
            this.status = UserStatus.WAIT;
        } else {
            this.status = UserStatus.NON_ACTIVE;
        }

        return this;
    }
}
