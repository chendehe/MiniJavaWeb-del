package com.chendehe.cloud.feign.controller;

import com.chendehe.cloud.feign.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  @Autowired
  UserServiceImpl service;

  @DeleteMapping("/{id}")
  public String delete(@PathVariable String id) {
    System.out.println("===:" + id);
    service.delete(id);
    return null;
  }
}