package com.luxoft.logeek.benchmark.loop;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@BenchmarkMode(value = Mode.AverageTime)
public class TightLoopBenchmark {

    @Benchmark
    public void slow(Blackhole bh) {
        for (int i = 0; i < 100_000_000; i++) {
            consume(this::consume, i, bh);
        }
    }

    @Benchmark
    public void fast(Blackhole bh) {
        BlackholeIntConsumer consumer = this::consume;
        for (int i = 0; i < 100_000_000; i++) {
            consume(consumer, i, bh);
        }
    }

    private void consume(BlackholeIntConsumer bhConsumer, int value, Blackhole bh) {
        bhConsumer.consume(value, bh);
    }

    private void consume(int value, Blackhole bh) {
        bh.consume(value);
    }

    interface BlackholeIntConsumer {
        void consume(int value, Blackhole bh);
    }
}
