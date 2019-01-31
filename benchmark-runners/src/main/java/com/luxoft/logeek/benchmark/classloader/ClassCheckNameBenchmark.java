package com.luxoft.logeek.benchmark.classloader;

import com.luxoft.logeek.ClassFullNameChecker;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@OutputTimeUnit(TimeUnit.NANOSECONDS)
@BenchmarkMode(value = Mode.AverageTime)
@Fork(jvmArgsAppend = {"-Xms2g", "-Xmx2g"})
public class ClassCheckNameBenchmark {

  @Benchmark
  public boolean effective() {
    return ClassFullNameChecker.effectiveStartsWith(String.class, "java.lang");
  }

  @Benchmark
  public boolean ineffective() {
    return ClassFullNameChecker.ineffectiveStartsWith(String.class, "java.lang");
  }

}
