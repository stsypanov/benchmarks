package com.luxoft.logeek.benchmark.array;

import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toCollection;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(jvmArgsAppend = {"-XX:+UseParallelGC", "-Xms1g", "-Xmx1g"})
public class SubListToArrayBenchmark {

  @Benchmark
  public Integer[] list(Data holder) {
    return holder.list.toArray(new Integer[0]);
  }

  @Benchmark
  public Integer[] subList(Data holder) {
    return holder.list.subList(0, holder.size).toArray(new Integer[0]);
  }

  /**
   * Can be used for JDK < 11 to avoid performance penalty
   */
  @Benchmark
  public Integer[] workAround(Data holder) {
    Integer[] array = holder.list.toArray(new Integer[0]);
    array = Arrays.copyOf(array, holder.size);
    return array;
  }

  @State(Scope.Thread)
  public static class Data {
    ArrayList<Integer> list;

    @Param({"10", "100", "1000"})
    int size;

    @Setup
    public void setup() {
      list = IntStream
              .range(0, size)
              .boxed()
              .collect(toCollection(ArrayList::new));
    }
  }
}