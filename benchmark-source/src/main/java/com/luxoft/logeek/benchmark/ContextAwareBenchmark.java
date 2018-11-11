package com.luxoft.logeek.benchmark;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public abstract class ContextAwareBenchmark extends BaseBenchmark {
  protected AnnotationConfigApplicationContext context;

  protected void init(Class configurationClass) {
    super.init();
    context = new AnnotationConfigApplicationContext(configurationClass);
  }

  protected <T> T getBean(Class<T> beanClass) {
    return context.getBean(beanClass);
  }
}
