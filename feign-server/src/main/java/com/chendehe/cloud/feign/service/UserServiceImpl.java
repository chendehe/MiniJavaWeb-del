package com.chendehe.cloud.feign.service;

import java.util.Map;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * MINI-WEB 是任意的客户端名称，用于创建 Ribbon 负载均衡
 */
@FeignClient(name = "MINI-WEB", configuration = FeignLogConfig.class,
    fallback = UserServiceHystric.class)
public interface UserServiceImpl {

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  String findOne(@RequestParam(value = "id") String id);

  @RequestMapping(value = "/list", method = RequestMethod.GET)
  PageResult<UserVo> findAll(@RequestParam Map<String, Object> map);

  @RequestMapping(value = "/", method = RequestMethod.POST)
  UserVo save(@RequestBody UserVo vo);
}