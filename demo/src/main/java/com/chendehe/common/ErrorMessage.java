package com.chendehe.common;

import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
public final class ErrorMessage {

  private static final String DEFAULT = "can't find any messages";

  private static MessageSource messageSource;

  private ErrorMessage() {
  }

  /**
   * 注入静态变量.
   *
   * @param messageSource message
   */
  @Autowired
  private void setMessageSource(MessageSource messageSource) {
    ErrorMessage.messageSource = messageSource;
  }

  public static String message(String code, String... params) {
    return messageSource.getMessage(code, params, DEFAULT, Locale.getDefault());
  }

}
