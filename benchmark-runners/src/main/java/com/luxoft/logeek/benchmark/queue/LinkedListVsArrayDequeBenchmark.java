package com.luxoft.logeek.benchmark.queue;

import org.openjdk.jmh.annotations.*;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(jvmArgsAppend = {"-XX:+UseParallelGC", "-Xms1g", "-Xmx1g"})
public class LinkedListVsArrayDequeBenchmark {

  @Benchmark
  public Queue<Integer> addIntoQueue(Data data) {
    Queue<Integer> integers = data.newQueue();
    for (int i = 0; i < data.size; i++) {
      integers.add(i);
    }
    return integers;
  }

  @State(Scope.Thread)
  public static class Data {
    @Param({"10", "100", "1000"})
    private int size;

    @Param({"LinkedList", "ArrayDeque"})
    private String queueType;

    Queue<Integer> newQueue() {
      switch (queueType) {
        case "LinkedList":
          return new LinkedList<>();
        case "ArrayDeque":
          return new ArrayDeque<>();
        default:
          throw new RuntimeException();
      }
    }
  }
}
