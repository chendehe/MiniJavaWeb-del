package com.chendehe.cloud.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * ****************RabbitMQ*******************
 * 1、管理员身份安装otp_win64_20.3.exe
 * 2、安装rabbitmq-server-3.7.5.exe
 * 3、启动管理界面rabbitmq-plugins.bat enable rabbitmq_management
 * 4、访问http://localhost:15672/，帐号guest：guest
 * ****************Kafka*******************
 * 1、安装ZooKeeper
 * 2、安装Kafka
 * 3、分别默认配置(此时配置服务无需任何配置)启动ZooKeeper和Kafka
 * ****************************************
 * > 配置更新后使用http://localhost:1050/bus/refresh刷新
 * > 使用git wehhook自动更新！
 */
@SpringBootApplication
@EnableConfigServer
public class ConfigApplication {

  public static void main(String[] args) {
    SpringApplication.run(ConfigApplication.class, args);
  }
}