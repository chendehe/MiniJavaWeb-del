package com.chendehe.cloud.ribbon.controller;

import com.chendehe.cloud.ribbon.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  @Autowired
  private UserServiceImpl service;

  /**
   * 删除. 成功返回204.
   */
  @DeleteMapping("/{id}")
  ResponseEntity delete(@PathVariable String id) {
    service.delete(id);

    return null;
  }


}