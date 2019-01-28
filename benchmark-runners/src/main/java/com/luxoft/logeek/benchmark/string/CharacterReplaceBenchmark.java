package com.luxoft.logeek.benchmark.string;


import com.luxoft.logeek.string.CharacterReplacer;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;


@OutputTimeUnit(TimeUnit.NANOSECONDS)
@BenchmarkMode(value = Mode.AverageTime)
@Fork(jvmArgsAppend = {"-Xms2g", "-Xmx2g"})
public class CharacterReplaceBenchmark {

  @Benchmark
  public String manualReplace(Data data) {
    Class<String> klass = data.klass;
    return CharacterReplacer.ineffective(klass);
  }

  @Benchmark
  public String stringReplace(Data data) {
    Class<String> klass = data.klass;
    return CharacterReplacer.effective(klass);
  }

  @State(Scope.Thread)
  public static class Data {
    private final Class<String> klass = String.class;
  }
}
