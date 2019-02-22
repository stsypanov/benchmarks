package com.luxoft.logeek.benchmark.loop;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;

interface BlackholeIntConsumer {
    void consume(int value, Blackhole bh);
}

@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@BenchmarkMode(value = Mode.AverageTime)
public class TightLoopBenchmark implements BlackholeIntConsumer {

    @Benchmark
    public void slow(Blackhole bh) {
        for (int i = 0; i < 100_000_000; i++) {
            consume(i, bh, this::consume);
        }
    }

    @Benchmark
    public void fast(Blackhole bh) {
        BlackholeIntConsumer consumer = this::consume;
        for (int i = 0; i < 100_000_000; i++) {
            consume(i, bh, consumer);
        }
    }

    @Override
    public void consume(int value, Blackhole bh) {
        bh.consume(value);
    }

    private void consume(int value, Blackhole bh, BlackholeIntConsumer bhConsumer) {
        bhConsumer.consume(value, bh);
    }

}
