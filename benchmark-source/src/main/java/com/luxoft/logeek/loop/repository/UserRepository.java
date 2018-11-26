package com.luxoft.logeek.loop.repository;

import com.luxoft.logeek.loop.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
