package com.chendehe.cloud.feign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

//@EnableCircuitBreaker,监控，需要引入包spring-cloud-starter-netflix-hystrix
//可以用 hystrix-dashboard 可视化监控
@EnableCircuitBreaker
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
@ComponentScan("com.chendehe.cloud.feign")
public class FeignApplication {

  public static void main(String[] args) {
    SpringApplication.run(FeignApplication.class, args);
  }
}