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
    String englishStr = data.englishStr;
    String russianStr = data.russianStr;
    int beginIndex = data.beginIndex;
    int endIndex = data.endIndex;

    String substring = data.useRussian ?
            russianStr.substring(beginIndex, endIndex) :
            englishStr.substring(beginIndex, endIndex);

    return new StringBuilder().append('L').append(substring).append(';').toString();
  }

  @Benchmark
  public String concatSubString(Data data) {
    String englishStr = data.englishStr;
    String russianStr = data.russianStr;
    int beginIndex = data.beginIndex;
    int endIndex = data.endIndex;

    String substring = data.useRussian ?
            russianStr.substring(beginIndex, endIndex) :
            englishStr.substring(beginIndex, endIndex);

    return new StringBuilder().append('L').append(substring).append(';').toString();
  }

  @Benchmark
  public String appendBounds(Data data) {
    String englishStr = data.englishStr;
    String russianStr = data.russianStr;
    int beginIndex = data.beginIndex;
    int endIndex = data.endIndex;

    String appended = data.useRussian ? russianStr : englishStr;

    return new StringBuilder().append('L').append(appended, beginIndex, endIndex).append(';').toString();
  }

  @State(Scope.Thread)
  public static class Data {
    String englishStr;
    String russianStr;

    @Param({"true", "false"})
    boolean useRussian;

    @Param({"10", "100", "1000"})
    private int length;

    private int beginIndex;
    private int endIndex;

    private ThreadLocalRandom random = ThreadLocalRandom.current();

    @Setup
    public void setup() {
      englishStr = randomString("abcdefghijklmnopqrstuvwxyz");
      russianStr = randomString("абвгдеёжзиклмнопрстуфхцчшщьыъэюя");
      beginIndex = length / 4;
      endIndex = length / 4 * 3;
    }

    private String randomString(String alphabet) {
      char[] chars = alphabet.toCharArray();

      StringBuilder sb = new StringBuilder(length);
      for (int i = 0; i < length; i++) {
        char c = chars[random.nextInt(chars.length)];
        sb.append(c);
      }

      return sb.toString();
    }
  }

}
