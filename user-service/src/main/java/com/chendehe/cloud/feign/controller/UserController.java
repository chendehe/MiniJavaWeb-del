package com.chendehe.cloud.feign.controller;

import com.chendehe.cloud.feign.service.UserServiceImpl;
import com.chendehe.cloud.feign.vo.OrderVo;
import com.chendehe.cloud.feign.vo.Page;
import com.chendehe.cloud.feign.vo.PageResult;
import com.chendehe.cloud.feign.vo.UserVo;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

  @Value("${test.config}")
  private String config;

  @Autowired
  private UserServiceImpl service;

  @GetMapping("/{id}")
  public String findOne(@PathVariable String id) {
    LOGGER.info("user id:{}", id);
    String one = service.findOne(id);
    return "find user..." + config + ":" + one;
  }

  @GetMapping("/list")
  public ResponseEntity findAll(Page page) {
    LOGGER.info("user page:{}", page);
    Map<String, Object> map = new HashMap<>(2);
    map.put("currentPage", page.getCurrentPage());
    map.put("pageSize", page.getPageSize());
    PageResult<OrderVo> allOrder = service.findAll(map);
    return new ResponseEntity<>(allOrder, HttpStatus.OK);
  }

  @PostMapping("/")
  public ResponseEntity save(@RequestBody UserVo userVo) {
    LOGGER.info("user save:{}", userVo);
    OrderVo orderVo = new OrderVo();
    orderVo.setId("1123");
    orderVo.setName("ordrer111");
    return new ResponseEntity<>(service.save(orderVo), HttpStatus.CREATED);
  }
}