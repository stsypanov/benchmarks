package com.luxoft.logeek.benchmark.concurrency;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

/**
 * See related pull requests
 *
 * @link https://github.com/openjdk/jdk/pull/218
 * @link https://github.com/openjdk/jdk/pull/818
 * @link https://github.com/spring-projects/spring-framework/pull/25261
 * @link https://github.com/spring-projects/spring-framework/pull/25846
 */
@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@BenchmarkMode(value = Mode.AverageTime)
@Fork(jvmArgsAppend = {"-Xms2g", "-Xmx2g"})
public class VolatileFieldZeroingBenchmark {

  @Benchmark
  public Object explicitInit() {
    return new ExplicitInit();
  }

  @Benchmark
  public Object noInit() {
    return new NoInit();
  }

  private static class ExplicitInit {
    private volatile int count = 0;
  }

  private static class NoInit {
    private volatile int count;
  }

}
