package com.luxoft.logeek.benchmark.boxing;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;
import java.util.stream.LongStream;

/**
 * См. описание вопроса https://youtrack.jetbrains.com/issue/IDEA-189336
 * Изменение: https://github.com/spring-projects/spring-data-jpa/pull/269/files
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(jvmArgsAppend = {"-XX:+UseParallelGC", "-Xms1g", "-Xmx1g"})
public class BoxingBenchmark {

  @Benchmark
  public long primitive(Data data) {
    long accumulator = 0L;
    for (Long aLong : data.longs) {
      accumulator += aLong == null ? 0 : aLong;
    }
    return accumulator;
  }

  @Benchmark
  public Long wrapper(Data data) {
    Long accumulator = 0L;
    for (Long aLong : data.longs) {
      accumulator += aLong == null ? 0 : aLong; // проверить, создаёт ли новый Long добавление 0 + BigDecimal
    }
    return accumulator;
  }

  @State(Scope.Thread)
  public static class Data {
    @Param({"10", "100", "1000"})
    private int count;

    private Long[] longs;

    @Setup
    public void init() {
      longs = LongStream
              .range(0, count)
              .boxed()
              .map(l -> l % 2 == 0 ? l : null)
              .toArray(Long[]::new);
    }
  }
}
