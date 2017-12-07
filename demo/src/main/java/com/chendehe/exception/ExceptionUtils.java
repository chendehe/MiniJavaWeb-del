package com.chendehe.exception;

import com.chendehe.config.Message;

public class ExceptionUtils {

  /**
   * 异常处理工具.
   * @param errorCode 错误码
   */
  public static void exception(String errorCode) {
    try {
      throw new ValidationException(Message.message(errorCode));
    } catch (ValidationException e) {
      e.printStackTrace();
    }
  }

}
