package com.chendehe.exception;

import com.chendehe.config.Message;

public class ExceptionUtils {

  public static void exception(String errorCode) {
    try {
      throw new ValidationException(Message.message(errorCode));
    } catch (ValidationException e) {
      e.printStackTrace();
    }
  }

}
