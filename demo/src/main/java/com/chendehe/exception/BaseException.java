package com.chendehe.exception;

import com.chendehe.config.Message;

public class BaseException extends Exception {
  private String errorCode;

  public BaseException(String errorCode) {
    super(errorCode);
    this.errorCode = errorCode;
  }

  public String getErrorCode() {
    return errorCode;
  }
}
