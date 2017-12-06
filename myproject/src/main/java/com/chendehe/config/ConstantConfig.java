package com.chendehe.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

//@ConfigurationProperties(prefix = "server")
@Component
public class ConstantConfig {

  private String test1;

  private String test2;

  public String getTest1() {
    return test1;
  }

  public void setTest1(String test1) {
    this.test1 = test1;
  }

  public String getTest2() {
    return test2;
  }

  public void setTest2(String test2) {
    this.test2 = test2;
  }
}
