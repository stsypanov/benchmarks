package com.luxoft.logeek.dto.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class MyEntity {
  @Id
  private Long id;

  @Column
  private String name;


}
