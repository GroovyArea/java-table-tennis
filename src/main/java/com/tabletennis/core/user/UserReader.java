package com.tabletennis.core.user;

import com.tabletennis.core.common.PagedModel;

import java.util.Optional;

public interface UserReader {

    Optional<User> findBy(long userId);
    PagedModel<User> findAllBy(int page, int size);
}
