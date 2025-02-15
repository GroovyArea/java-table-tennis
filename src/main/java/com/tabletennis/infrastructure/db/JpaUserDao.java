package com.tabletennis.infrastructure.db;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserDao extends JpaRepository<UserRow, Long> {
}
