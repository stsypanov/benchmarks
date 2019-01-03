package com.luxoft.logeek.benchmark.concurrency;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(jvmArgsAppend = {"-XX:+UseParallelGC", "-Xms2g", "-Xmx2g"})
public class ConcurrentReferenceHashMapInstantiation {

  @Benchmark
  public Object constructor() {
    return new org.springframework.util.ConcurrentReferenceHashMap<>();
  }

}

