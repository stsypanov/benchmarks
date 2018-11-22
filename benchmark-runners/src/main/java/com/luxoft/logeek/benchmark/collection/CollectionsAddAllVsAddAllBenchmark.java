package com.luxoft.logeek.benchmark.collection;

import org.openjdk.jmh.annotations.*;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(jvmArgsAppend = {"-XX:+UseParallelGC", "-Xms2g", "-Xmx2g"})
public class CollectionsAddAllVsAddAllBenchmark {

  @Benchmark
  public boolean collectionsAddAll(Data data) {
    Collection<Integer> collection = data.freshCollection();
    Integer[] array = data.array;
    return Collections.addAll(collection, array);
  }

  @Benchmark
  public boolean addAll(Data data) {
    Collection<Integer> collection = data.freshCollection();
    List<Integer> arrayAsList = Arrays.asList(data.array);
    return collection.addAll(arrayAsList);
  }

  @State(Scope.Thread)
  public static class Data {
    private static final String ARRAY_LIST = "ArrayList";
    private static final String ARRAY_DEQUE = "ArrayDeque";
    private static final String HASH_SET = "HashSet";
    private static final String COPY_ON_WRITE_ARRAY_LIST = "CopyOnWriteArrayList";
    private static final String CONCURRENT_LINKED_DEQUE = "ConcurrentLinkedDeque";

    Integer array[];

    @Param({ARRAY_LIST, HASH_SET, ARRAY_DEQUE, COPY_ON_WRITE_ARRAY_LIST, CONCURRENT_LINKED_DEQUE})
    String collection;

    @Param({"10", "100", "1000"})
    int size;

    @Setup
    public void setup() {
      array = IntStream
              .range(0, size)
              .boxed()
              .toArray(Integer[]::new);
    }

    Collection<Integer> freshCollection() {
      switch (collection) {
        case ARRAY_LIST:
          return new ArrayList<>();
        case HASH_SET:
          return new HashSet<>();
        case ARRAY_DEQUE:
          return new ArrayDeque<>();
        case COPY_ON_WRITE_ARRAY_LIST:
          return new CopyOnWriteArrayList<>();
        case CONCURRENT_LINKED_DEQUE:
          return new ConcurrentLinkedQueue<>();
        default:
          throw new IllegalArgumentException(collection);
      }
    }
  }
}
