package com.luxoft.logeek.dto;

import com.luxoft.logeek.dto.entity.MyEntity;
import com.luxoft.logeek.dto.repository.MyRepository;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableAutoConfiguration
@EntityScan(basePackageClasses = MyEntity.class)
@EnableJpaRepositories(basePackageClasses = MyRepository.class)
public class DtoVsProjectionConfig {
}
