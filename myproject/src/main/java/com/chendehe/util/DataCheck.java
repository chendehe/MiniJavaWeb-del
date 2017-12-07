package com.chendehe.util;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Locale;
import javax.validation.ValidationException;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
public final class DataCheck {

  private static MessageSource messageSource;

  private DataCheck() {
  }

  /**
   * 注入静态变量.
   *
   * @param messageSource message
   */
  @Autowired
  public void setMessageSource(MessageSource messageSource) {
    DataCheck.messageSource = messageSource;
  }

  /**
   * 检查空字符串.
   *
   * @param str 字符串
   * @param code 错误编码
   */
  private static void checkStrEmpty(String str, String code) {
    if (StringUtils.isEmpty(str)) {
      throw new ValidationException(messageSource.getMessage(code, null, null, Locale.CHINA));
    }
  }

  /**
   * 检查trim后的空字符串.
   *
   * @param str 字符串
   * @param code 错误编码
   */
  public static void checkTrimStrEmpty(String str, String code) {
    checkStrEmpty(str, code);
    if (StringUtils.isEmpty(str.trim())) {
      throw new ValidationException(messageSource.getMessage(code, null, null, Locale.CHINESE));
    }
  }

  /**
   * 检查空对象.
   *
   * @param t 对象
   * @param code 错误编码
   */
  public static <T> void checkNull(T t, String code) {
    if (null == t) {
      throw new ValidationException(code);
    }
  }

  /**
   * 检查空集合.
   *
   * @param c 集合
   * @param code 错误编码
   */
  public static void checkCollectionEmpty(Collection c, String code) {
    if (CollectionUtils.isEmpty(c)) {
      throw new ValidationException(code);
    }
  }

  /**
   * 检查枚举类型.
   *
   * @param c 枚举类
   * @param t 枚举对象
   * @param code 错误编码
   */
  public static <T> void checkEnum(Class<?> c, T t, String code) {
    try {
      Enum e = (Enum) c.getMethod("forValue", new Class[]{t.getClass()})
          .invoke(c, new Object[]{t});
      if (null == e) {
        throw new ValidationException(code);
      }
    } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
      throw new ValidationException(code);
    }
  }
}
