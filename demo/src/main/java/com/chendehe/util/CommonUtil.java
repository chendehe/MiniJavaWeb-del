package com.chendehe.util;

import com.github.rholder.retry.Retryer;
import com.github.rholder.retry.RetryerBuilder;
import com.github.rholder.retry.StopStrategies;
import com.github.rholder.retry.WaitStrategies;
import com.google.common.collect.Maps;
import java.util.Comparator;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public final class CommonUtil {

  private CommonUtil() {
  }

  public static final Retryer<Boolean> RETRYER = RetryerBuilder
      .<Boolean>newBuilder()
      .retryIfException()
      // retry when return false
      .retryIfResult(result -> Objects.equals(result, Boolean.FALSE))
      // retry interval:ms
      .withWaitStrategy(
          WaitStrategies.fixedWait(1000, TimeUnit.MILLISECONDS))
      // retry time
      .withStopStrategy(StopStrategies.stopAfterAttempt(3))
      .build();

  public static Map<String, Integer> sortMapByValue(Map<String, Integer> map) {
    Map<String, Integer> result = Maps.newLinkedHashMap();
    map.entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByValue(Comparator.reverseOrder()))
        .forEach(e -> result.put(e.getKey(), e.getValue()));
    return result;
  }
}
