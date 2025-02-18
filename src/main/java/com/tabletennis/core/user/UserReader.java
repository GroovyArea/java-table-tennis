package com.tabletennis.core.user;

import com.tabletennis.core.common.PagedModel;

public interface UserReader {

    PagedModel<User> getPagedUsers(int page, int size);
}
