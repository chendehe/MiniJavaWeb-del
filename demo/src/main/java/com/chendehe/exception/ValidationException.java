package com.chendehe.exception;

public class ValidationException extends BaseException {

  public ValidationException(String errorCode, String... param) {
    super(errorCode, param);
  }

}