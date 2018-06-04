package com.chendehe.cloud.feign.service;

import com.chendehe.cloud.feign.vo.OrderVo;
import com.chendehe.cloud.feign.vo.PageResult;
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
  public PageResult<OrderVo> findAll(Map<String, Object> map) {
    System.out.println("get all " + map + " error!");
    return null;
  }

  @Override
  public OrderVo save(OrderVo vo) {
    System.out.println("save user " + vo + " error!");
    return null;
  }
}
