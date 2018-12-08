package com.luxoft.logeek.benchmark.loop;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class LoopBenchmark {
  @Param({"10", "100", "1000"})
  private int count;

  private int[] ints;

  @Setup
  public void init() {
    ints = IntStream.range(0, count).toArray();
  }

  @Benchmark
  @SuppressWarnings("ForLoopReplaceableByForEach")
  public void ineffective(Blackhole bh) {
    for (int i = 0; i < ints.length; i++) {
      bh.consume(ints[i]);
    }
  }

  @Benchmark
  public void effective(Blackhole bh) {
    for (int i : ints) {
      bh.consume(i);
    }
  }

}
