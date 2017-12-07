package com.chendehe.config;

import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

@Configuration
public class MybatisDataConfig {

  private DataSource dataSource;

  @Autowired
  public MybatisDataConfig(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  /**
   * 必须指定名称 sqlSessionFactory.
   */
  @Bean("sqlSessionFactory")
  public SqlSessionFactory getBean() throws Exception {
    SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
    factoryBean.setDataSource(dataSource);

    ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
    factoryBean.setMapperLocations(resolver.getResources("classpath*:mapper/*.xml"));
    factoryBean.setTypeAliasesPackage("com.chendehe.entity");
    return factoryBean.getObject();
  }

}
