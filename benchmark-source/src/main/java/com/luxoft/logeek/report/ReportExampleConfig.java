package com.luxoft.logeek.report;

import com.luxoft.logeek.report.entity.ReportEntity;
import com.luxoft.logeek.report.repository.ReportRepository;
import com.p6spy.engine.spy.P6DataSource;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@EnableAutoConfiguration
@EntityScan(basePackageClasses = ReportEntity.class)
@EnableJpaRepositories(basePackageClasses = ReportRepository.class)
public class ReportExampleConfig {

  @Bean
  public DataSource dataSource() {
    EmbeddedDatabase origin = new EmbeddedDatabaseBuilder()
            .setType(EmbeddedDatabaseType.H2)
            .build();
    return new P6DataSource(origin);
  }

}
