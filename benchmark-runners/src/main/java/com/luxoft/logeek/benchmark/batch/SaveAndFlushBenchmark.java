package com.luxoft.logeek.benchmark.batch;

import com.luxoft.logeek.benchmark.ContextAwareBenchmark;
import com.luxoft.logeek.flush.SaveAndFlushExampleConfig;
import com.luxoft.logeek.flush.entity.SimpleEntity;
import com.luxoft.logeek.flush.repository.SimpleRepository;
import com.luxoft.logeek.flush.saver.SimpleSaver;
import org.openjdk.jmh.annotations.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@BenchmarkMode(value = Mode.SingleShotTime)
public class SaveAndFlushBenchmark extends ContextAwareBenchmark {
  private SimpleSaver simpleSaver;
  private SimpleRepository repository;

  @Param({"10", "100","1000"})
  private int entityCount;

  @Setup
  public void init() {
    super.init(SaveAndFlushExampleConfig.class);
    simpleSaver = getBean(SimpleSaver.class);
    repository = getBean(SimpleRepository.class);
  }

  @TearDown
  public void tearDown() {
    repository.deleteAllInBatch();
    super.closeContext();
  }

  @Benchmark
  public List<SimpleEntity> bulkSave() {
    return simpleSaver.bulkSave(entityCount);
  }

  @Benchmark
  public List<SimpleEntity> bulkSaveUsingFlush() {
    return simpleSaver.bulkSaveUsingFlush(entityCount);
  }
}