package com.chendehe.cloud.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * 网关，访问方式
 *  查询路由映射 http://localhost:1030/routes
 *  更详细 /routes?format=details
 *  查询过滤器 http://localhost:1030/filters
 */
@EnableZuulProxy
@SpringBootApplication
public class ZuulApplication {

  public static void main(String[] args) {
    SpringApplication.run(ZuulApplication.class, args);
  }
}