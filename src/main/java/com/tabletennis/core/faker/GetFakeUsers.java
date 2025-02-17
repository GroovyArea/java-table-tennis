package com.tabletennis.core.faker;

import java.util.List;

public interface GetFakeUsers {

    List<FakeUser> get(
            int seed,
            int quantity
    );
}
