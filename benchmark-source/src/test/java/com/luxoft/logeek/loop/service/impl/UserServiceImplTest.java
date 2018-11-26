package com.luxoft.logeek.loop.service.impl;

import com.luxoft.logeek.loop.LoopVsSingleCallConfig;
import com.luxoft.logeek.loop.dto.UserDto;
import com.luxoft.logeek.loop.entity.UserEntity;
import com.luxoft.logeek.loop.repository.UserRepository;
import com.luxoft.logeek.loop.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

@Commit
@Transactional
@RunWith(SpringRunner.class)
@ActiveProfiles("application-flush-postgres")
@SpringBootTest(classes = LoopVsSingleCallConfig.class)
public class UserServiceImplTest {
  @Autowired
  private UserService userService;
  @Autowired
  private UserRepository repository;

  private List<UserDto> dtos;
  private int entityCount = 10;

  @Before
  public void setUp() {
    List<UserEntity> users = new ArrayList<>(entityCount);

    for (long i = 1; i < entityCount + 1; i++) {
      UserEntity user = new UserEntity(i);
      users.add(user);
    }

    dtos = repository.saveAll(users).stream().map(UserEntity::getId).map(UserDto::new).collect(Collectors.toList());
  }

  @Test
  public void findInLoop() {
    Set<UserEntity> users = userService.findInLoop(dtos);
    assertEquals(entityCount, users.size());
  }

  @Test
  public void findWithSingleCall() {
    List<UserEntity> users = userService.findWithSingleCall(dtos);
    assertEquals(entityCount, users.size());
  }
}