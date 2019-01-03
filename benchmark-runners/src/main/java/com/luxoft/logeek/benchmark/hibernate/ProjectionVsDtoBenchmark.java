package com.luxoft.logeek.benchmark.hibernate;

import com.luxoft.logeek.benchmark.ContextAwareBenchmark;
import com.luxoft.logeek.dto.DtoVsProjectionConfig;
import com.luxoft.logeek.dto.domain.HasIdAndName;
import com.luxoft.logeek.dto.domain.IdNameDto;
import com.luxoft.logeek.dto.entity.MyEntity;
import com.luxoft.logeek.dto.repository.MyRepository;
import org.openjdk.jmh.annotations.*;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@BenchmarkMode(value = Mode.SingleShotTime)
public class ProjectionVsDtoBenchmark extends ContextAwareBenchmark {
  private MyRepository repository;
  private ThreadLocalRandom random;
  private List<Long> ids;

  private int entityCount = 1000;

  @Setup
  public void init() {
    super.init(DtoVsProjectionConfig.class);
    random = ThreadLocalRandom.current();
    repository = getBean(MyRepository.class);
    List<MyEntity> entities = LongStream.range(1, entityCount + 1)
            .boxed()
            .map(randomLong -> new MyEntity(randomLong, "ivan"))
            .collect(Collectors.toList());

    ids = repository.saveAll(entities).stream().map(MyEntity::getId).collect(Collectors.toList());
  }

  @TearDown
  public void tearDown() {
    repository.deleteAllInBatch();
    super.closeContext();
  }

  @Benchmark
  public IdNameDto findAllByNameUsingDto() {
    return repository.findDtoById(ids.get(random.nextInt(0, entityCount - 1)));
  }

  @Benchmark
  public HasIdAndName findAllByName() {
    return repository.findProjectionById(ids.get(random.nextInt(100)));
  }
}