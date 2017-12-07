package com.chendehe.util;

import com.chendehe.exception.ValidationException;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

public final class DataCheck {

  private DataCheck() {
  }

  /**
   * 检查空字符串.
   *
   * @param str 字符串
   * @param code 错误编码
   */
  private static void checkStrEmpty(String str, String code, String... param) {
    if (StringUtils.isEmpty(str)) {
      throw new ValidationException(code, param);
    }
  }

  /**
   * 检查trim后的空字符串.
   *
   * @param str 字符串
   * @param code 错误编码
   */
  public static void checkTrimStrEmpty(String str, String code, String... param) {
    checkStrEmpty(str, code, param);
    if (StringUtils.isEmpty(str.trim())) {
      throw new ValidationException(code, param);
    }
  }

  /**
   * 检查空对象.
   *
   * @param t 对象
   * @param code 错误编码
   */
  public static <T> void checkNull(T t, String code, String... param) {
    if (null == t) {
      throw new ValidationException(code, param);
    }
  }

  /**
   * 检查空集合.
   *
   * @param c 集合
   * @param code 错误编码
   */
  public static void checkCollectionEmpty(Collection c, String code, String... param) {
    if (CollectionUtils.isEmpty(c)) {
      throw new ValidationException(code, param);
    }
  }

  /**
   * 检查枚举类型.
   *
   * @param c 枚举类
   * @param t 枚举对象
   * @param code 错误编码
   */
  public static <T> void checkEnum(Class<?> c, T t, String code, String... param) {
    try {
      Enum e = (Enum) c.getMethod("forValue", new Class[]{t.getClass()})
          .invoke(c, new Object[]{t});
      if (null == e) {
        throw new ValidationException(code, param);
      }
    } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
      throw new ValidationException(code, param);
    }
  }
}
