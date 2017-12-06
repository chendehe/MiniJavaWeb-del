package com.chendehe.util;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

public final class DataCheck {

  private DataCheck() {
  }

  public static void checkStrEmpty(String str, String code) {
    if (StringUtils.isEmpty(str)) {
    }
  }

  public static void checkTrimStrEmpty(String str, String code) {
    checkStrEmpty(str, code);
    if (StringUtils.isEmpty(str.trim())) {
    }
  }

  public static <T> void checkNull(T t, String code) {
    if (null == t) {
    }
  }

  public static void checkCollectionEmpty(Collection c, String code) {
    if (CollectionUtils.isEmpty(c)) {
    }
  }

  public static <T> void checkEnum(Class<?> c, T t, String code) {
    try {
      Enum e = (Enum) c.getMethod("forValue", new Class[]{t.getClass()})
          .invoke(c, new Object[]{t});
      if (null == e) {
      }
    } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
    }
  }
}
