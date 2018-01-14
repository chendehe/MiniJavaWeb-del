package com.chendehe.cloud.feign.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "MINI-WEB", fallback = UserServiceHystric.class)
public interface UserServiceImpl {
  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  void delete(@RequestParam(value = "id") String id);
}