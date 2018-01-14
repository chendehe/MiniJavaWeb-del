package com.chendehe.cloud.feign.service;

import org.springframework.stereotype.Component;

@Component
public class UserServiceHystric implements UserServiceImpl {
  @Override
  public void delete(String id) {
    System.out.println("delete " + id + " error!");
  }
}
