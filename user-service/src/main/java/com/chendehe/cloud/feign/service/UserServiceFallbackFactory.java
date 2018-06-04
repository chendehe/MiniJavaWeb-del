package com.chendehe.cloud.feign.service;

import com.chendehe.cloud.feign.vo.OrderVo;
import com.chendehe.cloud.feign.vo.PageResult;
import feign.hystrix.FallbackFactory;
import java.util.Map;
import org.springframework.stereotype.Component;

/**
 * 利用fallbackfactory检查回退原因
 */
@Component
public class UserServiceFallbackFactory implements FallbackFactory<UserServiceImpl> {

  /**
   * FallbackFactory 的接口方法
   */
  @Override
  public UserServiceImpl create(Throwable cause) {
    return new UserServiceImpl() {
      @Override
      public String findOne(String id) {
        System.out.println("fallback reason was:" + cause);
        return "Error";
      }

      @Override
      public PageResult<OrderVo> findAll(Map<String, Object> map) {
        //TODO:
        return null;
      }

      @Override
      public OrderVo save(OrderVo vo) {
        //TODO:
        return null;
      }
    };
  }
}
