package com.luxoft.logeek.report.entity;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReportEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Lob
  private byte[] reportContent;

  public ReportEntity(byte[] reportContent) {
    this.reportContent = reportContent;
  }
}
