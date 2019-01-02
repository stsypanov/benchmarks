package com.luxoft.logeek.benchmark.concurrency;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class ConcurrentHashMapBenchmark {

  private Integer key = 1;
  private Function<Integer, Integer> function = key -> key + 1;

  private ConcurrentHashMap<Integer, Integer> map = new ConcurrentHashMap<>();

  @Benchmark
  @Threads(2)
  public Integer computeIfAbsent() {
    return map.computeIfAbsent(key, function);
  }

  @Benchmark
  @Threads(2)
  public Integer getAndPut() {
    Integer key = this.key;
    Integer value = map.get(key);
    if (value == null) {
      value = function.apply(key);
      map.put(key, value);
    }
    return value;
  }

}
