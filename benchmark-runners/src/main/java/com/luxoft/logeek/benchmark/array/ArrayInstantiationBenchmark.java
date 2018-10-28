package com.luxoft.logeek.benchmark.array;

import org.openjdk.jmh.annotations.*;

import java.lang.reflect.Array;
import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
//@Fork(jvmArgsAppend = {"-XX:+UseParallelGC", "-Xms1g", "-Xmx1g"})
public class ArrayInstantiationBenchmark {

  @Param({"5", "10", "100"})
  int length;

  @Benchmark
  public Object newInstance() {
    return Array.newInstance(Integer.class, length);
  }

  @Benchmark
  public Object constructor() {
    return new Integer[length];
  }

}
