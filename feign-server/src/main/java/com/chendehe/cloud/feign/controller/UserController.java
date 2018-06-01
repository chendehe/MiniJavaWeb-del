package com.chendehe.cloud.feign.controller;

import com.chendehe.cloud.feign.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  @Autowired
  UserServiceImpl service;

  @GetMapping("/{id}")
  public String get(@PathVariable String id) {
    System.out.println("id:" + id);
    return service.get(id);
  }
}