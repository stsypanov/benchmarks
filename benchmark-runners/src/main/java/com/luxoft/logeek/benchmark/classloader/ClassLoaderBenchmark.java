package com.luxoft.logeek.benchmark.classloader;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.SingleShotTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class ClassLoaderBenchmark {

  @Benchmark
  public Class ownClass() {
    return getClass();
  }

  @Benchmark
  public Class foreignClass() {
    return A.class;
  }
}
