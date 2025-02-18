package com.tabletennis.infrastructure.db.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserDao extends JpaRepository<UserRow, Long> {
}
