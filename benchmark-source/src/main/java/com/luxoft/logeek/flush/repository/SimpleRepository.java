package com.luxoft.logeek.flush.repository;

import com.luxoft.logeek.flush.entity.SimpleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SimpleRepository extends JpaRepository<SimpleEntity, Long> {
}
