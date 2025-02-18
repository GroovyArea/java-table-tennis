package com.tabletennis.core.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class InitUser {

    private final UserWriter userWriter;

    public void init(List<User> users) {
        List<User> initUsers = users.stream()
                .map(user ->
                        User.builder()
                                .fakerId(user.getFakerId())
                                .name(user.getName())
                                .email(user.getEmail())
                                .build()
                                .ofWithStatus()
                ).toList();

        userWriter.saveAll(initUsers);
    }
}
