package com.marsmob.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


/**
 * 扫描当前类以及指定的包
 * 默认当前类的包和子包的类都被扫描
 * 我们指定其扫描的包路径即可。
 * @author Administrator
 *
 */
@SpringBootApplication
@ComponentScan("com.**.controller,com.**.service*,com.**.config,com.marsmob.redis")
@MapperScan("com.**.dao")
public class SpringBootMain {
  public static void main(String[] args) {
	  SpringApplication.run(SpringBootMain.class, args);
 }
}
 