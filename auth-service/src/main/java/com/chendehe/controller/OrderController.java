package com.chendehe.controller;

import com.chendehe.vo.OrderVo;
import com.chendehe.vo.Page;
import com.chendehe.vo.PageResult;
import com.google.common.collect.Lists;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * POST 请求 http://localhost:1111/refresh 动态刷新配置
 */
@RestController
@RefreshScope
public class OrderController {

  private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

  @Value("${test.config}")
  private String config;

  @GetMapping("/list")
  public ResponseEntity findAll(Page page) {
    LOGGER.info("[OrderController] id is:{}", page);
    PageResult<OrderVo> result = new PageResult<>();
    List<OrderVo> orderVoList = Lists.newArrayList();
    OrderVo order = new OrderVo();
    order.setId("11111");
    order.setName("order");
    orderVoList.add(order);
    result.setList(orderVoList);
    result.setCurrentPage(1);
    result.setPageSize(10);
    result.setTotalNum(10);
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public String findOne(@PathVariable String id) {
    LOGGER.info("[OrderController] id is:{}", id);
    return "find order..." + config;
  }

  @PostMapping("/")
  public ResponseEntity save(@RequestBody OrderVo orderVo) {
    LOGGER.info("[OrderController] order is:{}", orderVo);
    return new ResponseEntity<>(orderVo, HttpStatus.CREATED);
  }

  /**
   * 查找详情. 成功返回200.
   */
  @GetMapping("/session")
  public Object sessionId(HttpServletRequest request) {
    return request.getSession().getId();
  }
}