package com.chendehe.cloud.feign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

//@EnableCircuitBreaker,监控，需要引入包spring-cloud-starter-netflix-hystrix
//可以用 hystrix-dashboard 可视化监控
@EnableCircuitBreaker
//Edgware可忽略配置@EnableDiscoveryClient，引入包即可
@EnableFeignClients/*(defaultConfiguration = FeignConfig.class)*/
@SpringBootApplication
@ComponentScan("com.chendehe.cloud.feign")
public class FeignApplication {

  public static void main(String[] args) {
    SpringApplication.run(FeignApplication.class, args);
  }
}