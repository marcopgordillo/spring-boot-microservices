package com.example.catalogservice.utils;

import com.netflix.hystrix.strategy.HystrixPlugins;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategy;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;

@Slf4j
//@Component
public class ContextCopyHystrixConcurrencyStrategy extends HystrixConcurrencyStrategy {

  public ContextCopyHystrixConcurrencyStrategy() {
    HystrixPlugins.getInstance().registerConcurrencyStrategy(this);
  }

  @Override
  @SuppressWarnings("unused")
  public <T> Callable<T> wrapCallable(Callable<T> callable) {
    return new MyCallable(callable, MyThreadLocalsHolder.getCorrelationId());
  }

  public static class MyCallable<T> implements Callable<T> {

    private final Callable<T> actual;
    private final String correlationId;

    public MyCallable(Callable<T> callable, String correlationId) {
      this.actual = callable;
      this.correlationId = correlationId;
    }

    @Override
    public T call() throws Exception {
      log.info("-----------call()------------------");
      MyThreadLocalsHolder.setCorrelationId(correlationId);
      try {
        return actual.call();
      } finally {
        MyThreadLocalsHolder.setCorrelationId(null);
      }
    }
  }
}
