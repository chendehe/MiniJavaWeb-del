package com.chendehe.cloud.feign.service;

import feign.Logger.Level;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Feign 配置类，配置日志级别为FULL
 */
@Configuration
public class FeignLogConfig {

  @Bean
  Level feignLoggerLevel() {
    return Level.FULL;
  }
}
