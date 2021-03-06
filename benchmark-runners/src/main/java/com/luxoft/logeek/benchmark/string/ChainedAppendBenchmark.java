package com.luxoft.logeek.benchmark.string;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(jvmArgsAppend = {"-Xms2g", "-Xmx2g"})
public class ChainedAppendBenchmark {
  private final String str1 = "1".repeat(10);
  private final String str2 = "2".repeat(10);
  private final String str3 = "3".repeat(10);
  private final String str4 = "4".repeat(10);
  private final String str5 = "5".repeat(10);

  @Benchmark
  @SuppressWarnings("ALL")
  public String chainedAppend() {
    return new StringBuilder()
            .append(str1)
            .append(str2)
            .append(str3)
            .append(str4)
            .append(str5)
            .toString();
  }

  @Benchmark
  @SuppressWarnings("ALL")
  public String tornAppend() {
    StringBuilder sb = new StringBuilder()
            .append(str1)
            .append(str2)
            .append(str3);

    sb.append(str4);

    return sb.append(str5).toString();
  }

  @Benchmark
  @SuppressWarnings("ALL")
  public String newLineAppend() {
    StringBuilder sb = new StringBuilder();
    sb.append(str1);
    sb.append(str2);
    sb.append(str3);
    sb.append(str4);
    sb.append(str5);
    return sb.toString();
  }

  @Benchmark
  public String concatenation() {
    return str1 + str2 + str3 + str4 + str5;
  }
}
