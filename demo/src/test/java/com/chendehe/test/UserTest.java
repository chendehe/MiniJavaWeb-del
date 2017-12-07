package com.chendehe.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import com.chendehe.service.UserService;
import com.chendehe.vo.Page;
import com.chendehe.vo.PageResult;
import com.chendehe.vo.UserVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * SpringRunner指明是测试类 SpringBootTest创建ApplicationContext,其他的还有@…​Test
 * 使用随机端口配置RANDOM_PORT，其他MOCK、DEFINED_PORT、NONE 自动扫描同一路径下的class文件进行测试 导入测试依赖的类
 * 自定义配置@Import(MyTestsConfiguration.class)  @AutoConfigure… exclude
 * 排除测试@ImportAutoConfiguration(exclude = UserService.class)
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserTest {

  // controller
  @Autowired
  private TestRestTemplate template;

  // mock
  @MockBean
  private UserService userService;

  @Test
  public void controllerTest() {
    String body = template.getForObject("/", String.class);
    assertThat(body).isEqualTo("Hello World!");
  }

  @Test
  public void mockTest() {
    given(userService.findAll(new Page())).willReturn(new PageResult<>());
    PageResult<UserVo> all = userService.findAll(new Page());
    assertThat(all).isNull();
  }

}
