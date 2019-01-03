package com.luxoft.logeek.benchmark;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

public abstract class ContextAwareBenchmark extends BaseBenchmark {
  protected ConfigurableApplicationContext context;

  protected ConfigurableApplicationContext init(Class configurationClass) {
    super.init();
    return SpringApplication.run(configurationClass);
  }

  protected void closeContext() {
    context.close();
  }

  protected <T> T getBean(Class<T> beanClass) {
    return context.getBean(beanClass);
  }
}
