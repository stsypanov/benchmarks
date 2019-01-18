package com.luxoft.logeek.benchmark.concurrency;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * NB! Remember to specify number of thread in BenchmarkRunner
 */
@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class AtomicVsSynchronizedBenchmark {

  private AtomicCounter atomicCounter = new AtomicCounter();
  private SynchronizedCounter synchCounter = new SynchronizedCounter();

  @Benchmark
  public long atomic() {
    return atomicCounter.incrementAndGet();
  }

  @Benchmark
  public long synch() {
    return synchCounter.incrementAndGet();
  }

  private static final class SynchronizedCounter {
    private volatile long count;

    private synchronized long incrementAndGet() {
      return ++count;
    }
  }

  private static final class AtomicCounter {
    private AtomicLong count = new AtomicLong();

    private long incrementAndGet() {
      return count.incrementAndGet();
    }
  }
}
