package com.luxoft.logeek.loop;

import com.luxoft.logeek.loop.entity.UserEntity;
import com.luxoft.logeek.loop.repository.UserRepository;
import com.luxoft.logeek.loop.service.UserService;
import com.luxoft.logeek.loop.service.impl.UserServiceImpl;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableAutoConfiguration
@EntityScan(basePackageClasses = UserEntity.class)
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class LoopVsSingleCallConfig {

  @Bean
  public UserService userService(UserRepository userRepository) {
    return new UserServiceImpl(userRepository);
  }

}
