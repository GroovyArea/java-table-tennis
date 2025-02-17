package com.tabletennis.core.user;

import com.tabletennis.infrastructure.db.UserRow;

import java.util.List;

public interface UserWriter {

    void deleteAll();
    void saveAll(List<UserRow> userRows);
}
