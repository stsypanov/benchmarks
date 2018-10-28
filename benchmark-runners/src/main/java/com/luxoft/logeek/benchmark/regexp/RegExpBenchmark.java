package com.luxoft.logeek.benchmark.regexp;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(jvmArgsAppend = {"-XX:+UseParallelGC", "-Xms1g", "-Xmx1g"})
public class RegExpBenchmark {
  Pattern ineffective = Pattern.compile("\\D+");
  Pattern effective = Pattern.compile("\\D++");

  @Benchmark
  public boolean measureIneffective10() {
    return ineffective.matcher("azaza1olo").matches();
  }

  @Benchmark
  public boolean measureEffective10() {
    return effective.matcher("azaza1olo").matches();
  }
}
