package com.tabletennis.api.command;

public record InitRequest(
        int seed,
        int quantity
) { }
