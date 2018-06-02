package com.chendehe.cloud.turbine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.turbine.EnableTurbine;
import org.springframework.context.annotation.ComponentScan;

//聚合 HystrixDashboard 数据
//此外，可以结合MQ来使用：Hystrix的监控数据发至MQ，Turbine消费MQ的数据
@EnableTurbine
@SpringBootApplication
@ComponentScan("com.chendehe.cloud.turbine")
public class TurbineApplication {

  public static void main(String[] args) {
    SpringApplication.run(TurbineApplication.class, args);
  }
}