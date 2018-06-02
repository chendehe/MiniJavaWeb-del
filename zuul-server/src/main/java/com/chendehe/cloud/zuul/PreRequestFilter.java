package com.chendehe.cloud.zuul;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 自定义filter
 */
@Component
public class PreRequestFilter extends ZuulFilter {

  private static final Logger LOGGER = LoggerFactory.getLogger(PreRequestFilter.class);

  /**
   * pre、route、post、error等
   */
  @Override
  public String filterType() {
    return "pre";
  }

  /**
   * 指定过滤器的执行顺序
   */
  @Override
  public int filterOrder() {
    return 1;
  }

  /**
   * true表示要执行
   */
  @Override
  public boolean shouldFilter() {
    return true;
  }

  /**
   * 过滤器的具体逻辑
   */
  @Override
  public Object run() {
    RequestContext ctx = RequestContext.getCurrentContext();
    HttpServletRequest request = ctx.getRequest();
    LOGGER.info(String.format("send %s request to %s", request.getMethod(), request.getRequestURL().toString()));
//    鉴权检验部分
//    Object accessToken = request.getParameter("token");
//    if (accessToken == null) {
//      LOGGER.warn("token is empty");
//      ctx.setSendZuulResponse(false);
//      ctx.setResponseStatusCode(401);
//      try {
//        ctx.getResponse().getWriter().write("token is empty");
//      } catch (Exception e) {
//        System.err.println("error!!!");
//      }
//
//      return null;
//    }
    LOGGER.info("ok");
    return null;
  }
}
