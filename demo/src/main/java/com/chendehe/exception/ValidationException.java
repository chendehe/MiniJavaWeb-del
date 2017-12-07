package com.chendehe.exception;

import com.chendehe.config.Message;

public class ValidationException extends RuntimeException {
  static final long serialVersionUID = -7034897190745766949L;
  private String errorCode;

  public ValidationException(String errorCode) {
    super(Message.message(errorCode));
    this.errorCode = errorCode;
  }

  public String getErrorCode() {
    return errorCode;
  }
}