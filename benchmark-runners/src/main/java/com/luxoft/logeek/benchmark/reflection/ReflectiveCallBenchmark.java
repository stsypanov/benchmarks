package com.luxoft.logeek.benchmark.reflection;

import org.openjdk.jmh.annotations.*;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(jvmArgsAppend = {"-XX:+UseParallelGC", "-Xms1g", "-Xmx1g"})
public class ReflectiveCallBenchmark {

  @Benchmark
  public Object invoke(Data data) throws Exception {
    return data.getBoolean.invoke(data);
  }

  @State(Scope.Thread)
  public static class Data {
    Method getBoolean;

    @Setup
    public void setup() throws Exception {
      getBoolean = getClass().getMethod("getBoolean");
    }

    Boolean getBoolean() {
      return Boolean.TRUE;
    }
  }
}
