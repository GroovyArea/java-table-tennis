package com.tabletennis.infrastructure.http.faker;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange
public interface FakerClient {

    @GetExchange("/api/v1/users")
    public FakerApiResponse getUsers(
            @RequestParam(name = "_seed") int seed,
            @RequestParam(name = "_quantity") int quantity,
            @RequestParam(name = "_locale") String locale
    );
}
