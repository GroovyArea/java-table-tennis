package com.tabletennis.infrastructure.db.user;

import com.tabletennis.core.user.User;
import com.tabletennis.core.user.UserWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class DBUserWriter implements UserWriter {

    private final JpaUserDao jpaUserDao;
    private final JdbcUserDao jdbcUserDao;

    @Override
    public void deleteAll() {
        jpaUserDao.deleteAll();
    }

    @Override
    public void saveAll(List<User> users) {
        var userRows = users.stream()
                .map(this::mapToJpaEntity)
                .toList();

        jdbcUserDao.saveInBatch(userRows);
    }

    private UserRow mapToJpaEntity(User user) {
        return UserRow.builder()
                .fakerId(user.getFakerId())
                .name(user.getName())
                .email(user.getEmail())
                .status(user.getStatus())
                .build();
    }
}
