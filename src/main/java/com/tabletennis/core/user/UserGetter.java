package com.tabletennis.core.user;

import com.tabletennis.api.common.ApiErrorException;
import com.tabletennis.api.common.MetaCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserGetter {

    private final UserReader userReader;

    public User get(long userId) {
        return userReader.findBy(userId).orElseThrow(() -> new ApiErrorException(MetaCode.CREATED));
    }
}
