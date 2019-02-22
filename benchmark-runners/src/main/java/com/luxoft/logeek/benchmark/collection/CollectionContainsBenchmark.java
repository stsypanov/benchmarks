package com.luxoft.logeek.benchmark.collection;

import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(jvmArgsAppend = {"-Xms2g", "-Xmx2g"})
public class CollectionContainsBenchmark {

  @Benchmark
  public boolean array(Data data) {
    Integer value = data.value;
    ArrayList<Integer> integers = data.integers;
    return integers.contains(value);
  }

  @Benchmark
  public boolean set(Data data) {
    Integer value = data.value;
    ArrayList<Integer> integers = data.integers;
    return new HashSet<>(integers).contains(value);
  }

  @State(Scope.Thread)
  public static class Data {

    @Param({"10", "100", "1000", "10000", "100000", "1000000", "10000000"})
    private int size;

    private Integer value;
    private ArrayList<Integer> integers;

    @Setup
    public void setup() {
      integers = IntStream.range(0, size)
              .boxed()
              .collect(Collectors.toCollection(ArrayList::new));

      Collections.shuffle(integers);

      value = integers.get(size - 1);
    }

  }
}
