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

    private long id;
    private long fakerId;
    private String name;
    private String email;
    private UserStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
