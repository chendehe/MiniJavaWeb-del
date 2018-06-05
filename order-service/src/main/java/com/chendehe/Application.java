package com.chendehe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

@SpringBootApplication
@EnableCircuitBreaker
@EnableFeignClients
@EnableOAuth2Client
@ComponentScan("com.chendehe")
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

}
