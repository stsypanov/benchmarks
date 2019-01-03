package com.luxoft.logeek.benchmark.string;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class StringBuilderAppendBenchmark {

  @Benchmark
  @SuppressWarnings("StringBufferReplaceableByString")
  public String appendSubString(Data data) {
    String str = data.str;
    int beginIndex = data.beginIndex;
    int endIndex = data.endIndex;

    String substring = str.substring(beginIndex, endIndex);

    return new StringBuilder().append('L').append(substring).append(';').toString();
  }

  @Benchmark
  public String concatSubString(Data data) {
    String str = data.str;
    int beginIndex = data.beginIndex;
    int endIndex = data.endIndex;

    String substring = str.substring(beginIndex, endIndex);

    return "L" + substring + ';';
  }

  @Benchmark
  public String appendBounds(Data data) {
    String str = data.str;
    int beginIndex = data.beginIndex;
    int endIndex = data.endIndex;

    return new StringBuilder().append('L').append(str, beginIndex, endIndex).append(';').toString();
  }

  @State(Scope.Thread)
  public static class Data {
    String str;

    @Param({"10", "100", "1000"})
    private int length;

    private int beginIndex;
    private int endIndex;

    @Setup
    public void setup() {
      str = randomString();
      beginIndex = length / 4;
      endIndex = length / 4* 3;
    }

    private String randomString() {
      ThreadLocalRandom random = ThreadLocalRandom.current();
      char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();

      StringBuilder sb = new StringBuilder(length);
      for (int i = 0; i < length; i++) {
        char c = chars[random.nextInt(chars.length)];
        sb.append(c);
      }
      return sb.toString();
    }
  }

}
