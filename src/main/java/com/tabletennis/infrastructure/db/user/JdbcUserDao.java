package com.tabletennis.infrastructure.db.user;

import java.util.List;

public interface JdbcUserDao {

    void saveInBatch(List<UserRow> userRows);
}
