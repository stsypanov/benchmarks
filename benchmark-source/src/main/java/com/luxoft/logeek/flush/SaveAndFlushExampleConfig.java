package com.luxoft.logeek.flush;

import com.luxoft.logeek.flush.entity.SimpleEntity;
import com.luxoft.logeek.flush.repository.SimpleRepository;
import com.luxoft.logeek.flush.saver.SimpleSaver;
import com.luxoft.logeek.flush.saver.SimpleSaverImpl;
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
@EntityScan(basePackageClasses = SimpleEntity.class)
@EnableJpaRepositories(basePackageClasses = SimpleRepository.class)
public class SaveAndFlushExampleConfig {

  @Bean
  public DataSource dataSource() {
    return new EmbeddedDatabaseBuilder()
            .setType(EmbeddedDatabaseType.H2)
            .build();
  }

  @Bean
  public SimpleSaver simpleSaver(SimpleRepository repository) {
    return new SimpleSaverImpl(repository);
  }

}
