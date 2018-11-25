package com.luxoft.logeek.flush;

import com.luxoft.logeek.flush.entity.SimpleEntity;
import com.luxoft.logeek.flush.repository.SimpleRepository;
import com.luxoft.logeek.flush.saver.SimpleSaver;
import com.luxoft.logeek.flush.saver.SimpleSaverImpl;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableAutoConfiguration
@EntityScan(basePackageClasses = SimpleEntity.class)
@EnableJpaRepositories(basePackageClasses = SimpleRepository.class)
public class SaveAndFlushExampleConfig {

  @Bean
  public SimpleSaver simpleSaver(SimpleRepository repository) {
    return new SimpleSaverImpl(repository);
  }

}
