package com.tabletennis.infrastructure.db.user;

import com.tabletennis.core.common.PagedModel;
import com.tabletennis.core.user.User;
import com.tabletennis.core.user.UserReader;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DbUserReader implements UserReader {

    private final JpaUserDao jpaUserDao;

    @Override
    public PagedModel<User> getPagedUsers(int page, int size) {
        var pageable = PageRequest.of(page, size);
        Page<UserRow> userRowPage = jpaUserDao.findAll(pageable);

        var users = userRowPage.getContent()
                .stream()
                .map(this::mapToEntity)
                .toList();

        return PagedModel.<User>builder()
                .totalElements(userRowPage.getTotalElements())
                .totalPages(userRowPage.getTotalPages())
                .data(users)
                .build();
    }

    private User mapToEntity(UserRow userRow) {
        return User.builder()
                .id(userRow.getId())
                .name(userRow.getName())
                .email(userRow.getEmail())
                .fakerId(userRow.getFakerId())
                .status(userRow.getStatus())
                .createdAt(userRow.getCreatedAt())
                .updatedAt(userRow.getUpdatedAt())
                .build();
    }
}
