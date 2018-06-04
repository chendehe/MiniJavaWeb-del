package com.chendehe.cloud.feign.controller;

import com.chendehe.cloud.feign.vo.Page;
import com.chendehe.cloud.feign.service.UserServiceImpl;
import com.chendehe.cloud.feign.vo.UserVo;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

  @Autowired
  UserServiceImpl service;

  @GetMapping("/{id}")
  public String findOne(@PathVariable String id) {
    LOGGER.info("id:{}", id);
    return service.findOne(id);
  }

  @GetMapping("/list")
  public ResponseEntity findAll(Page page) {
    LOGGER.info("page:{}", page);
    Map<String, Object> map = new HashMap<>(2);
    map.put("currentPage", page.getCurrentPage());
    map.put("pageSize", page.getPageSize());
    return new ResponseEntity<>(service.findAll(map), HttpStatus.OK);
  }

  @PostMapping("/")
  public ResponseEntity save(@RequestBody UserVo userVo) {
    LOGGER.info("save:{}", userVo);
    return new ResponseEntity<>(service.save(userVo), HttpStatus.CREATED);
  }
}