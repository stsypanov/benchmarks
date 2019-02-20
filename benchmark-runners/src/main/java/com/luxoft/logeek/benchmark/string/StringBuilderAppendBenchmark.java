package com.luxoft.logeek.benchmark.string;

import com.luxoft.logeek.utils.RandomStringGenerator;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(jvmArgsAppend = {"-Xms2g", "-Xmx2g"})
public class StringBuilderAppendBenchmark {

  @Benchmark
  @SuppressWarnings("StringBufferReplaceableByString")
  public String appendSubString(Data data) {
    String latinStr = data.latinStr;
    String nonLatinStr = data.nonLatinStr;
    int beginIndex = data.beginIndex;
    int endIndex = data.endIndex;

    String substring = data.nonLatin ?
            nonLatinStr.substring(beginIndex, endIndex) :
            latinStr.substring(beginIndex, endIndex);

    return new StringBuilder()
            .append('L')
            .append(substring)
            .append(';')
            .toString();
  }

  @Benchmark
  @SuppressWarnings("StringBufferReplaceableByString")
  public String appendBounds(Data data) {
    String latinStr = data.latinStr;
    String nonLatinStr = data.nonLatinStr;
    int beginIndex = data.beginIndex;
    int endIndex = data.endIndex;

    String appended = data.nonLatin ? nonLatinStr : latinStr;

    return new StringBuilder()
            .append('L')
            .append(appended, beginIndex, endIndex)
            .append(';')
            .toString();
  }

  @State(Scope.Thread)
  public static class Data {
    String latinStr;
    String nonLatinStr;

    @Param({"true", "false"})
    boolean nonLatin;

    @Param({"5", "10", "50", "100", "500", "1000"})
    private int length;

    private int beginIndex;
    private int endIndex;

    @Setup
    public void setup() {
      RandomStringGenerator generator = new RandomStringGenerator();

      latinStr = generator.randomString("abcdefghijklmnopqrstuvwxyz", length);
      nonLatinStr = generator.randomString("абвгдеёжзиклмнопрстуфхцчшщьыъэюя", length);

      beginIndex = 1;
      endIndex = length + 1;
    }

  }

}
