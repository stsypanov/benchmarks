package com.luxoft.logeek.benchmark;

import com.luxoft.logeek.benchmark.array.ArrayInstantiationBenchmark;
import com.luxoft.logeek.benchmark.array.ZeroingEliminationBenchmark;
import com.luxoft.logeek.benchmark.batch.SaveAndFlushBenchmark;
import com.luxoft.logeek.benchmark.boxing.BoxingBenchmark;
import com.luxoft.logeek.benchmark.collection.CollectionsAddAllVsAddAllBenchmark;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

public class BenchmarkRunner {

  public static void main(String[] args) throws Exception {
    Options opt = new OptionsBuilder()
            .include(ArrayInstantiationBenchmark.class.getSimpleName())
            .include(BoxingBenchmark.class.getSimpleName())
            .include(SaveAndFlushBenchmark.class.getSimpleName())
            .include(ZeroingEliminationBenchmark.class.getSimpleName())
            .include(CollectionsAddAllVsAddAllBenchmark.class.getSimpleName())
            .warmupIterations(10)
            .warmupTime(TimeValue.seconds(1))
            .measurementIterations(10)
            .measurementTime(TimeValue.seconds(1))
            .forks(10) //0 makes debugging possible
            .shouldFailOnError(true)
//				.shouldDoGC(false)
//				.jvmArgsAppend(
//						"-Xint"
//						,
//						"-XX:+UnlockDiagnosticVMOptions",
//						"-XX:+PrintCompilation",
//						"-XX:+PrintInlining",
//						"-XX:+LogCompilation"
//				)
//            .addProfiler(GCProfiler.class)// memory and GC profiler
            .build();

    new Runner(opt).run();
  }
}