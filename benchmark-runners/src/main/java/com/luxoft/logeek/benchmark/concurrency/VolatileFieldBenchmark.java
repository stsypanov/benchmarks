package com.luxoft.logeek.benchmark.concurrency;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@BenchmarkMode(value = Mode.AverageTime)
@Fork(jvmArgsAppend = {"-Xms2g", "-Xmx2g"})
public class VolatileFieldBenchmark {

  @Benchmark
  public Object explicitInit() {
    return new ExplicitInit();
  }

  @Benchmark
  public Object noInit() {
    return new NoInit();
  }

  private static class ExplicitInit {

    private volatile boolean field = false;

    public boolean isField() {
      return field;
    }

    public void setField(boolean field) {
      this.field = field;
    }
  }

  private static class NoInit {

    private volatile boolean field;

    public boolean isField() {
      return field;
    }

    public void setField(boolean field) {
      this.field = field;
    }
  }

}
