package com.tabletennis.core.user;

import com.tabletennis.core.user.vo.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * User Domain Model
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private long id;
    private long fakerId;
    private String name;
    private String email;
    private UserStatus status;
}
