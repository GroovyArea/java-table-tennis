package com.tabletennis.core.user;

import com.tabletennis.infrastructure.db.user.UserRow;

import java.util.List;

public interface UserWriter {

    void deleteAll();
    void saveAll(List<UserRow> userRows);
}
