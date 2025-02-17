package com.tabletennis.infrastructure.http.faker;

import com.tabletennis.core.faker.FakeUser;
import com.tabletennis.core.faker.GetFakeUsers;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class HttpGetFakeUsers implements GetFakeUsers {

    private final FakerClient fakerClient;

    @Override
    public List<FakeUser> get(
            int seed,
            int quantity
    ) {
        try {
            var defaultLocale = "ko_KR";
            var users = fakerClient.getUsers(seed, quantity, defaultLocale);

            return users
                    .data()
                    .stream()
                    .map(user -> new FakeUser(user.id(), user.username(), user.email()))
                    .toList();
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            return new ArrayList<>();
        }
    }
}
