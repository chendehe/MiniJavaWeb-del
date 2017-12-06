package com.chendehe.controller;

import com.alibaba.fastjson.JSONObject;
import com.chendehe.service.UserService;
import com.chendehe.vo.Page;
import com.chendehe.vo.PageResult;
import com.chendehe.vo.UserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

  private UserService service;

  @Autowired
  public UserController(UserService service) {
    this.service = service;
  }

  /**
   * 查找列表.
   */
  @GetMapping("/list")
  PageResult<UserVo> findAll(Page page) {
    LOGGER.info("[UserController] id is:{}", page);
    PageResult<UserVo> result = service.findAll(page);
    return result;
  }

  /**
   * 查找详情.
   */
  @GetMapping("/{id}")
  UserVo findOne(@PathVariable String id) {
    LOGGER.info("[UserController] id is:{}", id);
    return service.findOne(id);
  }

  /**
   * 新建.
   */
  @PostMapping("/")
  UserVo save(@RequestBody UserVo userVo) {
    LOGGER.info("[UserController] user is:{}", JSONObject.toJSON(userVo));
    return service.save(userVo);
  }

  /**
   * 更新.
   */
  @PutMapping("/{id}")
  UserVo update(@RequestBody UserVo userVo, @PathVariable String id) {
    LOGGER.info("[UserController] user is:{}, id is:{}", JSONObject.toJSON(userVo), id);
    userVo.setId(id);
    return service.update(userVo);
  }

  /**
   * 删除.
   */
  @DeleteMapping("/{id}")
  JSONObject delete(@PathVariable String id) {
    LOGGER.info("[UserController] id is:{}", id);
    service.delete(id);
    JSONObject json = new JSONObject();
    json.put("status", "success");
    return json;
  }

}