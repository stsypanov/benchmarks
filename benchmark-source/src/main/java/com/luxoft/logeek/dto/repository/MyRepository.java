package com.luxoft.logeek.dto.repository;

import com.luxoft.logeek.dto.domain.IdNameDto;
import com.luxoft.logeek.dto.domain.HasIdAndName;
import com.luxoft.logeek.dto.entity.MyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MyRepository extends JpaRepository<MyEntity, Long> {

  @Query("select e.id, e.name " +
          " from MyEntity e " +
          "where e.id = :id")
  HasIdAndName findProjectionById(@Param("id") Long id);

  @Query("select new com.luxoft.logeek.dto.domain.IdNameDto(e.id, e.name) " +
          " from MyEntity e " +
          "where e.id = :id")
  IdNameDto findDtoById(@Param("id") Long id);

}
