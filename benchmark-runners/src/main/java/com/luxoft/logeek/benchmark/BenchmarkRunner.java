package com.luxoft.logeek.benchmark;

import com.luxoft.logeek.benchmark.hibernate.ProjectionVsDtoBenchmark;
import org.openjdk.jmh.profile.GCProfiler;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

public class BenchmarkRunner {

  public static void main(String[] args) throws Exception {
    Options opt = new OptionsBuilder()
//				.include(CowSubListRemoveBenchmark.class.getSimpleName())
//				.include(UselessSortedBenchmark.class.getSimpleName())
//				.include(NaiveBenchmark.class.getSimpleName())
//				.include(TreeMapPutBenchmark.class.getSimpleName())
//				.include(AnyMatchVsContainsBenchmark.class.getSimpleName())
//				.include(ArrayConstructorVsNewInstanceBenchmark.class.getSimpleName())
//				.include(StringEmptinessBenchmark.class.getSimpleName())
//				.include(VarArgCallBenchmark.class.getSimpleName())
//				.include(StringFormatBenchmark.class.getSimpleName())
//            .include(BoxingBenchmark.class.getSimpleName())
            .include(ProjectionVsDtoBenchmark.class.getSimpleName())
//				.include(RegExpBenchmark.class.getSimpleName())
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
            .addProfiler(GCProfiler.class)// memory and GC profiler
            .build();

    new Runner(opt).run();
  }
}