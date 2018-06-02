package com.chendehe.cloud.hystrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.ComponentScan;

//可以用 hystrix-dashboard 可视化监控
@EnableHystrixDashboard
@SpringBootApplication
@ComponentScan("com.chendehe.cloud.hystrix")
public class HystrixApplication {

  public static void main(String[] args) {
    SpringApplication.run(HystrixApplication.class, args);
  }
}