package com.luxoft.logeek.benchmark.string;

import com.luxoft.logeek.string.Joiner;
import com.luxoft.logeek.utils.RandomStringGenerator;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

import java.util.concurrent.TimeUnit;

@OutputTimeUnit(TimeUnit.NANOSECONDS)
@BenchmarkMode(value = Mode.AverageTime)
@Fork(jvmArgsAppend = {"-Xms2g", "-Xmx2g"})
public class StringBuilderVsStringJoinerBenchmark {

  @Benchmark
  public String stringBuilder(Data data) {
    String[] stringArray = data.stringArray;

    return Joiner.joinWithStringBuilder(stringArray);
  }

  @Benchmark
  public String stringJoiner(Data data) {
    String[] stringArray = data.stringArray;

    return Joiner.joinWithStringJoiner(stringArray);
  }

  @State(Scope.Thread)
  public static class Data {

    @Param({"true", "false"})
    private boolean latin;

    @Param({"10", "100", "1000"})
    private int length;

    private String[] stringArray;

    @Setup
    public void setup() {
      final int arrayLength = 10;

      stringArray = new String[arrayLength];

      RandomStringGenerator generator = new RandomStringGenerator();

      String alphabet = latin ? "abcdefghijklmnopqrstuvwxyz" : "абвгдеёжзиклмнопрстуфхцчшщьыъэюя";

      for (int i = 0; i < arrayLength; i++) {
        String string = generator.randomString(alphabet, length);
        stringArray[i] = string;
      }
    }

  }
}
