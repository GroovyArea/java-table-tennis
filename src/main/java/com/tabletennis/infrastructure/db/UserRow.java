package com.tabletennis.infrastructure.db;

import com.tabletennis.core.user.vo.UserStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "users")
public class UserRow extends EntityBase {

    @Column(name = "faker_id")
    private Long fakerId;

    private String name;

    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private UserStatus status;
}

