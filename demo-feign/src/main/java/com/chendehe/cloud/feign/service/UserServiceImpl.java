package com.chendehe.cloud.feign.service;

import com.chendehe.cloud.feign.vo.PageResult;
import com.chendehe.cloud.feign.vo.UserVo;
import java.util.Map;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * demo 是任意的客户端名称，用于创建 Ribbon 负载均衡
 * 普通回滚 fallback = UserServiceFallback.class
 * 带原因的回滚，更加灵活，可根据异常类型区别处理 fallbackFactory = UserServiceFallbackFactory.class
 */
@FeignClient(name = "demo", fallbackFactory = UserServiceFallbackFactory.class)
public interface UserServiceImpl {

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  String findOne(@RequestParam(value = "id") String id);

  @RequestMapping(value = "/list", method = RequestMethod.GET)
  PageResult<UserVo> findAll(@RequestParam Map<String, Object> map);

  @RequestMapping(value = "/", method = RequestMethod.POST)
  UserVo save(@RequestBody UserVo vo);
}