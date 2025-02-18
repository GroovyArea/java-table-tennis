package com.tabletennis.core.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteUser {

    private final UserWriter userWriter;

    public void deleteAll() {
        userWriter.deleteAll();
    }
}
