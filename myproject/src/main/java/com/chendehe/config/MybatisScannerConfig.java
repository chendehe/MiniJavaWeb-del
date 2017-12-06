package com.chendehe.config;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>MybatisScannerConfig 和 MybatisDataConfig 需要分开写.</p>
 * <p>而 MybatisScannerConfig 比 MybatisDataConfig 加载早.</p>
 * <p>所以要定义@AutoConfigureAfter.</p>
 * <p>否则 MybatisDataConfig 先加载会找不到 dataSource.</p>
 * @see MybatisDataConfig
 */
@Configuration
@AutoConfigureAfter(MybatisDataConfig.class)
public class MybatisScannerConfig {

  /**
   * 基于Java配置sqlSessionFactory，扫描dao层.
   * @return 配置实例
   */
  @Bean
  public MapperScannerConfigurer getConfig() {
    MapperScannerConfigurer configurer = new MapperScannerConfigurer();
    configurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
    configurer.setBasePackage("com.chendehe.dao");
    return configurer;
  }


}
