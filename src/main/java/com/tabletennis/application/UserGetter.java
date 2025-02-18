package com.tabletennis.application;

import com.tabletennis.api.response.TotalUsersResponse;
import com.tabletennis.core.common.PagedModel;
import com.tabletennis.core.user.User;
import com.tabletennis.core.user.UserReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserGetter {

    private final UserReader userReader;

    public TotalUsersResponse getPaged(
            int size,
            int page
    ) {
        var pagedUsers = userReader.getPagedUsers(page, size);

        var userList = pagedUsers.data()
                .stream()
                .map(this::toResponse)
                .toList();

        return TotalUsersResponse.builder()
                .totalElements(pagedUsers.totalElements())
                .totalPages(pagedUsers.totalPages())
                .userList(userList)
                .build();
    }

    private TotalUsersResponse.User toResponse(User user) {
        return TotalUsersResponse.User.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .fakerId(user.getFakerId())
                .status(user.getStatus())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }
}
