package com.chendehe.cloud.feign.service;

import com.chendehe.cloud.feign.vo.PageResult;
import com.chendehe.cloud.feign.vo.UserVo;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class UserServiceFallback implements UserServiceImpl {

  @Override
  public String findOne(String id) {
    System.out.println("get " + id + " error!");
    return "Error";
  }

  @Override
  public PageResult<UserVo> findAll(Map<String, Object> map) {
    System.out.println("get all " + map + " error!");
    return null;
  }

  @Override
  public UserVo save(UserVo vo) {
    System.out.println("save user " + vo + " error!");
    return null;
  }
}
