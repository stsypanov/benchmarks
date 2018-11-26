package com.luxoft.logeek.benchmark.loop;

import com.luxoft.logeek.benchmark.ContextAwareBenchmark;
import com.luxoft.logeek.loop.LoopVsSingleCallConfig;
import com.luxoft.logeek.loop.dto.UserDto;
import com.luxoft.logeek.loop.entity.UserEntity;
import com.luxoft.logeek.loop.repository.UserRepository;
import com.luxoft.logeek.loop.service.UserService;
import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Measure retrieving collection of entities in loop one by one
 */
@BenchmarkMode(Mode.SingleShotTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class LoopVsSingleCallBenchmark {

  @Benchmark
  public Set<UserEntity> findInLoop(Data data) {
    return data.service.findInLoop(data.dtos);
  }

  @Benchmark
  public List<UserEntity> findWithSingleCall(Data data) {
    return data.service.findWithSingleCall(data.dtos);
  }

  @State(Scope.Thread)
  public static class Data extends ContextAwareBenchmark {
    @Param({"10", "100", "1000", "10000"})
    private int entityCount;

    private UserRepository repository;
    private UserService service;

    private List<UserDto> dtos;

    @Setup
    public void init() {
      super.init(LoopVsSingleCallConfig.class);
      service = context.getBean(UserService.class);
      repository = context.getBean(UserRepository.class);
      populateTable();
    }


    @TearDown
    public void tearDown() {
      repository.deleteAllInBatch();
      context.close();
    }

    private void populateTable() {
      ArrayList<UserEntity> users = new ArrayList<>(entityCount);

      for (long i = 1; i < entityCount + 1; i++) {
        UserEntity UserEntity = new UserEntity(i);
        users.add(UserEntity);
      }

      dtos = repository.saveAll(users).stream().map(UserEntity::getId).map(UserDto::new).collect(Collectors.toList());
    }
  }
}
