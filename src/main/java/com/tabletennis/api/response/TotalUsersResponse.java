package com.tabletennis.api.response;

import com.tabletennis.core.user.vo.UserStatus;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record TotalUsersResponse(
        long totalElements,
        int totalPages,
        List<User> userList
) {

    @Builder
    public record User(
            long id,
            long fakerId,
            String name,
            String email,
            UserStatus status,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
    }
}
