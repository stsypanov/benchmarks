package com.luxoft.logeek.benchmark.array;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

/**
 * В спецификации (https://docs.oracle.com/javase/specs/jls/se10/html/jls-10.html#jls-10.6) указано,
 * что при создании массива его элементы принимают значения по умолчанию, в частности int[]
 * заполнен нулями (для прочих типов см. https://docs.oracle.com/javase/specs/jls/se10/html/jls-4.html#jls-4.12.5)
 * Тем не менее, иногда встречается избыточный (и поэтому несколько менее производительный) код,
 * подобный описанному в методе {@link #manualZeroingElimination}
 */
@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class ZeroingEliminationBenchmark {

  @Param({"10", "100", "1000"})
  private int size;

  @Benchmark
  public int[] manualZeroingElimination() {
    int[] array = new int[size];
    for (int i = 0; i < array.length; i++) {
      array[i] = 0;
    }
    return array;
  }

  @Benchmark
  public int[] defaultZeroingElimination() {
    int[] array = new int[size];
    return array;
  }

}
