package com.luxoft.logeek.benchmark.queue;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class ArrayDequeVsStackBenchmark {

  @Benchmark
  public Collection<Integer> pushIntoStack(Data data) {
    Stack<Integer> stack = new Stack<>();
    data.integers.forEach(stack::push);
    return stack;
  }

  @Benchmark
  public Collection<Integer> pushIntoArrayDeque(Data data) {
    ArrayDeque<Integer> stack = new ArrayDeque<>();
    data.integers.forEach(stack::push);
    return stack;
  }

  @Benchmark
  public void popFromStack(Data data, Blackhole bh) {
    Stack<Integer> stack = data.nonEmptyStack();
    while (!stack.isEmpty()) {
      bh.consume(stack.pop());
    }
  }

  @Benchmark
  public void popFromArrayDeque(Data data, Blackhole bh) {
    ArrayDeque<Integer> stack = data.nonEmptyArrayDeque();
    while (!stack.isEmpty()) {
      bh.consume(stack.pop());
    }
  }

  @Benchmark
  public Integer peekFromStack(Data data) {
    return data.stack.peek();
  }

  @Benchmark
  public Integer peekFromArrayDeque(Data data) {
    return data.arrayDeque.peek();
  }

  @State(Scope.Thread)
  public static class Data {
    @Param({"10", "100", "1000"})
    private int size;

    private Stack<Integer> stack;
    private ArrayDeque<Integer> arrayDeque;
    private List<Integer> integers;

    @Setup
    public void setup() {
      stack = new Stack<>();
      arrayDeque = new ArrayDeque<>();
      integers = IntStream.range(0, size).boxed().collect(Collectors.toList());
      integers.forEach(stack::push);
      integers.forEach(arrayDeque::push);

    }

    private Stack<Integer> nonEmptyStack() {
      Stack<Integer> stack = new Stack<>();
      stack.addAll(integers);
      return stack;
    }

    private ArrayDeque<Integer> nonEmptyArrayDeque() {
      return new ArrayDeque<>(integers);
    }

  }
}
