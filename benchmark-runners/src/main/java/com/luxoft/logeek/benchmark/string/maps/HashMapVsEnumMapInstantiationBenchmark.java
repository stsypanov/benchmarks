package com.luxoft.logeek.benchmark.string.maps;

import org.openjdk.jmh.annotations.*;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(jvmArgsAppend = {"-Xms2g", "-Xmx2g"})
public class HashMapVsEnumMapInstantiationBenchmark {

  @Benchmark
  public Object hashMap() {
    var map = new HashMap<>();
    map.put(Constants.MarginLeft, 1);
    map.put(Constants.MarginRight, 2);
    map.put(Constants.MarginTop, 3);
    map.put(Constants.MarginBottom, 4);
    return map;
  }

  @Benchmark
  public Object enumMap() {
    var map = new EnumMap<>(ConstantsEnum.class);
    map.put(ConstantsEnum.MarginLeft, 1);
    map.put(ConstantsEnum.MarginRight, 2);
    map.put(ConstantsEnum.MarginTop, 3);
    map.put(ConstantsEnum.MarginBottom, 4);
    return map;
  }

}
