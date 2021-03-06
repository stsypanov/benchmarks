package com.luxoft.logeek.benchmark.p6spy;

import com.luxoft.logeek.p6spy.ToHexStringConverter;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Fork(jvmArgsAppend = {"-XX:+UseParallelGC", "-Xms2g", "-Xmx2g"})
public class SizedStringBuilderBenchmark {

  private byte[] bytes;
  private ToHexStringConverter converter;

  @Setup
  public void init() {
    bytes = new byte[1024 * 1024 * 20];
    converter = new ToHexStringConverter();
    ThreadLocalRandom.current().nextBytes(bytes);
  }

  @Benchmark
  public String original() {
    return converter.toHexString(bytes);
  }

  @Benchmark
  public String patched() {
    return converter.patched_toHexString(bytes);
  }

  @Benchmark
  public String chars() {
    return converter.chars_toHexString(bytes);
  }

}
