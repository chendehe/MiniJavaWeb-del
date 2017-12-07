package com.chendehe.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * 国际化读取.
 */
@Configuration
public class MessageConfig {

  /**
   * 国际化处理.
   * @return messageSource
   */
  @Bean("messageSource")
  public MessageSource getMessage() {
    ResourceBundleMessageSource source = new ResourceBundleMessageSource();
    source.setBasenames("i18/messages");
    source.setDefaultEncoding("utf-8");
    return source;
  }
}
