package com.luxoft.logeek.benchmark.string;

import com.luxoft.logeek.string.Joiner;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

import java.util.concurrent.ThreadLocalRandom;
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

      String[] latinStrArray = new String[arrayLength];
      String[] nonLatinStrArray = new String[arrayLength];

      for (int i = 0; i < arrayLength; i++) {
        String latinStr = randomString("abcdefghijklmnopqrstuvwxyz", length);
        String nonLatinStr = randomString("абвгдеёжзиклмнопрстуфхцчшщьыъэюя", length);

        latinStrArray[i] = latinStr;
        nonLatinStrArray[i] = nonLatinStr;
      }

      stringArray = latin ? latinStrArray : nonLatinStrArray;
    }

    private String randomString(String alphabet, int length) {
      char[] chars = alphabet.toCharArray();

      ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();

      StringBuilder sb = new StringBuilder(length);
      for (int i = 0; i < length; i++) {
        char c = chars[threadLocalRandom.nextInt(chars.length)];
        sb.append(c);
      }

      return sb.toString();
    }
  }
}
