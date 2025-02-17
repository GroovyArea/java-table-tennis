package com.tabletennis.api.usecase;

import com.tabletennis.core.faker.FakeUser;
import com.tabletennis.core.faker.GetFakeUsers;
import com.tabletennis.core.room.DeleteRoom;
import com.tabletennis.core.user.DeleteUser;
import com.tabletennis.core.user.InitUserPolicy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class InitExecutor {

    private final DeleteRoom deleteRoom;
    private final DeleteUser deleteUser;
    private final InitUserPolicy initUserPolicy;
    private final GetFakeUsers getFakeUsers;

    public void execute(int seed, int quantity) {
        deleteRoom.deleteAll();
        deleteUser.deleteAll();
        initUsers(seed, quantity);
    }

    private void initUsers(int seed, int quantity) {
        List<FakeUser> fakeUsers = getFakeUsers.get(seed, quantity);

        List<InitUserPolicy.User> policyTargetUsers = fakeUsers.stream()
                .map(fakeUser -> new InitUserPolicy.User(
                        fakeUser.id(),
                        fakeUser.username(),
                        fakeUser.email()
                )).toList();

        initUserPolicy.init(policyTargetUsers);
    }
}
