package com.chendehe.util;

import java.util.Date;
import org.apache.commons.lang3.RandomUtils;

public final class IdGenerator {

  private IdGenerator() {
  }

  /** 生成主键id.
   * @return 时间戳
   */
  public static String get() {
    return new Date().getTime() + RandomUtils.nextInt(1000, 9999) + "";
  }

}
