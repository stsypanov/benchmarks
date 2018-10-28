package com.luxoft.logeek.report.repository;

import com.luxoft.logeek.report.entity.ReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<ReportEntity, Long> {
}
