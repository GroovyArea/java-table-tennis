package com.tabletennis.infrastructure.db;

import java.util.List;

public interface JdbcUserDao {

    void saveInBatch(List<UserRow> userRows);
}
