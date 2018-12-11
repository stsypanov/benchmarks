package com.luxoft.logeek.benchmark.concurrency;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@OutputTimeUnit(TimeUnit.NANOSECONDS)
@BenchmarkMode(value = Mode.AverageTime)
public class ArithmeticSimplificationBenchmark {

  @Benchmark
  public int slow(Data data) {
    int size = data.size;
    int initialCapacity = data.initialCapacity;
    return (int) ((initialCapacity + size - 1L) / size);
  }

  @Benchmark
  public int fast(Data data) {
    int size = data.size;
    int initialCapacity = data.initialCapacity;
    return (initialCapacity + size - 1) / size;
  }

  @State(Scope.Thread)
  public static class Data {
    int size = 3;
    int initialCapacity = 2;
  }
}
