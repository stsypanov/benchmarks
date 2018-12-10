package com.luxoft.logeek.benchmark.array;

import org.openjdk.jmh.annotations.*;

import java.lang.reflect.Array;
import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(jvmArgsAppend = {"-XX:+UseParallelGC", "-Xms2g", "-Xmx2g"})
public class ArrayInstantiationBenchmark {

  @Param({"10", "100", "1000"})
  private int length;

  @Benchmark
  public Object newInstance() {
    return Array.newInstance(Object.class, length);
  }

  @Benchmark
  public Object constructor() {
    return new Object[length];
  }

}
