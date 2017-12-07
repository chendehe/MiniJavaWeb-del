package com.chendehe.exception;

public class ExceptionUtils {

  /**
   * 异常处理工具.
   *
   * @param errorCode 错误码
   */
  public static void exception(String errorCode) {
    throw new BaseException(errorCode);
  }

}
