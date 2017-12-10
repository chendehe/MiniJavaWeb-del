package com.chendehe.config;

import com.chendehe.util.PropertieUtil;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Redis 配置信息.
 */
@Component
@ConfigurationProperties(prefix = "jedis")
public class RedisConfig {

  private String host;

  private int port;

  public String getHost() {
    return host;
  }

  public void setHost(String host) {
    this.host = host;
  }

  public int getPort() {
    return port;
  }

  public void setPort(int port) {
    this.port = port;
  }
}
