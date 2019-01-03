package com.luxoft.logeek.benchmark.queue;

import org.openjdk.jmh.annotations.*;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.stream.LongStream;

@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@BenchmarkMode(value = Mode.AverageTime)
public class ArrayDequeAsQueueBenchmark {

  @Param({"10", "100", "1000"})
  private int size;

  private Queue<Long> queue;
  private Queue<Long> preparedQueue;

  @Setup(Level.Invocation)
  public void setup() {
    queue = new ArrayDeque<>();
    preparedQueue = new ArrayDeque<>();
    LongStream.range(0, size).boxed().forEach(preparedQueue::add);
  }

  @Benchmark
  public Queue<Long> measureAdd() {
    LongStream.range(0, size).boxed().forEach(queue::add);
    return queue;
  }

  @Benchmark
  public Queue<Long> measureRemove() {
    LongStream.range(0, size).forEach(preparedQueue::remove);
    return preparedQueue;
  }

  @Benchmark
  public Queue<Long> measurePoll() {
    LongStream.range(0, size).forEach(aLong -> preparedQueue.poll());
    return preparedQueue;
  }

  @Benchmark
  public Queue<Long> measureElement() {
    LongStream.range(0, size).forEach(aLong -> preparedQueue.element());
    return preparedQueue;
  }
}
