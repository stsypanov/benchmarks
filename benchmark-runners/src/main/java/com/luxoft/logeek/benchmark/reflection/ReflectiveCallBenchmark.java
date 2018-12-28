package com.luxoft.logeek.benchmark.reflection;

import org.openjdk.jmh.annotations.*;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(jvmArgsAppend = {"-XX:+UseParallelGC", "-Xms2g", "-Xmx2g"})
public class ReflectiveCallBenchmark {

  private Method method;

  @SuppressWarnings("WeakerAccess")
  public boolean readOnly() {
    return true;
  }

  @Setup
  public void setup() throws Exception {
    method = getClass().getMethod("readOnly");
  }

  @Benchmark
  public Object invoke() throws Exception {
    return method.invoke(this);
  }

}
