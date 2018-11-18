package com.luxoft.logeek.benchmark.array;

import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toCollection;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(jvmArgsAppend = {"-XX:+UseParallelGC", "-Xms1g", "-Xmx1g"})
public class SubListToArrayBenchmark {

  private ArrayList<Integer> list;

  @Param({"0", "10", "100", "1000"})
  private int size;

  @Setup
  public void setup() {
    list = IntStream.range(0, size).boxed().collect(toCollection(ArrayList::new));
  }

  @Benchmark
  public Integer[] list() {
    ArrayList<Integer> list = this.list;
    return list.toArray(new Integer[0]);
  }

  @Benchmark
  public Integer[] subList() {
    ArrayList<Integer> list = this.list;
    return list.subList(0, list.size()).toArray(new Integer[0]);
  }

}