package com.chendehe.exception;

import com.chendehe.common.ErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExceptionUtils {

  private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionUtils.class);

  /**
   * 异常处理工具.
   *
   * @param e BaseException
   */
  public static void exception(Throwable e) {
    LOGGER.error("[Exception]: ", e);
    if (e instanceof BaseException) {
      throw new BaseException(((BaseException) e).getErrorCode(), ((BaseException) e).getParam());
    } else {
      throw new BaseException(ErrorCode.SYSTEM_ERROR);
    }
  }

}
