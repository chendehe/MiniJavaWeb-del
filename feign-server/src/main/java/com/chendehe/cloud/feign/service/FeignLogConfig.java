package com.chendehe.cloud.feign.service;

import feign.Logger.Level;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignLogConfig {

  @Bean
  Level feignLoggerLevel() {
    return Level.FULL;
  }
}
