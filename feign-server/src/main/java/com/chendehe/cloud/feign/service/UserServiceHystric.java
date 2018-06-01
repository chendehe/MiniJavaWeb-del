package com.chendehe.cloud.feign.service;

import org.springframework.stereotype.Component;

@Component
public class UserServiceHystric implements UserServiceImpl {

  @Override
  public String get(String id) {
    System.out.println("get " + id + " error!");
    return "Error";
  }
}
