package com.luxoft.logeek.benchmark.concatenation;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(jvmArgsAppend = {"-XX:+UseParallelGC", "-Xms2g", "-Xmx2g"})
public class ConcatenationVsFormatBenchmark {

  @Benchmark
  public String concatenation(Data data) {
    return data.str1 + ' ' + data.str2;
  }

  @Benchmark
  public String stringFormat(Data data) {
    return String.format("%s %s", data.str1, data.str2);
  }

  @State(Scope.Thread)
  public static class Data {
    String str1;
    String str2;
    @Param({"5", "10", "100", "200"})
    private int length;

    @Setup
    public void setup() {
      ThreadLocalRandom random = ThreadLocalRandom.current();
      char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();

      str1 = randomString(chars, random);
      str2 = randomString(chars, random);
    }

    private String randomString(char[] chars, ThreadLocalRandom random) {
      StringBuilder sb = new StringBuilder(length);
      for (int i = 0; i < length; i++) {
        char c = chars[random.nextInt(chars.length)];
        sb.append(c);
      }
      return sb.toString();
    }
  }
}
