package com.luxoft.logeek.benchmark;

import java.util.concurrent.ThreadLocalRandom;

public abstract class BaseBenchmark {
  protected ThreadLocalRandom random;

  protected void init() {
    random = ThreadLocalRandom.current();
  }
}
