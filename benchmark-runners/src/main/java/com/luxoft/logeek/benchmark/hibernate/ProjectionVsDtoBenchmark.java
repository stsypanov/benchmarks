package com.luxoft.logeek.benchmark.hibernate;

import com.luxoft.logeek.benchmark.ContextAwareBenchmark;
import com.luxoft.logeek.dto.DtoVsProjectionConfig;
import com.luxoft.logeek.dto.domain.HasIdAndName;
import com.luxoft.logeek.dto.domain.IdNameDto;
import com.luxoft.logeek.dto.entity.MyEntity;
import com.luxoft.logeek.dto.repository.MyRepository;
import org.openjdk.jmh.annotations.*;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@BenchmarkMode(value = Mode.AverageTime)
public class ProjectionVsDtoBenchmark extends ContextAwareBenchmark {
  private MyRepository repository;

  @Setup
  public void init() {
    super.init(DtoVsProjectionConfig.class);
    repository = getBean(MyRepository.class);
    List<MyEntity> entities = LongStream.range(1, 102)
            .boxed()
            .map(randomLong -> new MyEntity(randomLong, "ivan"))
            .collect(Collectors.toList());

    repository.saveAll(entities);
  }

  @Benchmark
  public List<IdNameDto> findAllByNameUsingDto() {
    return repository.findAllByNameIntoDto("ivan");
  }

  @Benchmark
  public List<HasIdAndName> findAllByName() {
    return repository.findAllByName("ivan");
  }
}