package com.chendehe.exception;

import com.chendehe.config.Message;

public class BaseException extends RuntimeException {

  private String errorCode;

  public BaseException(String errorCode) {
    super(Message.message(errorCode));
    this.errorCode = errorCode;
  }

  public String getErrorCode() {
    return errorCode;
  }
}
