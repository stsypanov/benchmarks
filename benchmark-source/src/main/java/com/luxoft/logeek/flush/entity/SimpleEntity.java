package com.luxoft.logeek.flush.entity;

import javax.persistence.*;

@Entity
public class SimpleEntity {
  @Id
  @SequenceGenerator(name = "hibernate_sequence", sequenceName = "hibernate_sequence")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hibernate_sequence")
  private Long id;

}
