package com.tabletennis.infrastructure.db.user;

import com.tabletennis.core.user.UserWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

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
    public void saveAll(List<UserRow> userRows) {
        jdbcUserDao.saveInBatch(userRows);
    }
}
