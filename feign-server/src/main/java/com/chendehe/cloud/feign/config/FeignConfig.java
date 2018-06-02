package com.chendehe.cloud.feign.config;

import feign.Logger.Level;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Feign 配置类
 */
@Configuration
public class FeignConfig {

  /**
   * 配置日志级别为FULL
   */
  @Bean
  Level feignLoggerLevel() {
    return Level.FULL;
  }

//  /**
//   * Java配置方式自定义负载均衡算法，或使用配置文件配置
//   */
//  @Bean
//  public IRule ribbonRule() {
//    return new RandomRule();
//  }
}
