package com.tabletennis.infrastructure.db;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "users")
public class UserRow extends EntityBase {

    private Long fakerId;

    private String name;

    private String email;

    @Enumerated(EnumType.STRING)
    private UserStatus status;
}

enum UserStatus {

    WAIT,
    ACTIVE,
    NON_ACTIVE;
}
