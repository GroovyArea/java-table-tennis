package com.tabletennis.infrastructure.db.user;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class JdbcUserDaoImpl implements JdbcUserDao {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void saveInBatch(List<UserRow> userRows) {
        var sql = "INSERT INTO users (faker_id, name, email, status, created_at, updated_at) VALUES (?, ?, ?, ?, current_timestamp, current_timestamp)";

        var params = userRows.stream()
                .map(userRow -> new Object[]{userRow.getFakerId(), userRow.getName(), userRow.getEmail(), userRow.getStatus().name()})
                .toList();

        jdbcTemplate.batchUpdate(sql, params);
    }
}
