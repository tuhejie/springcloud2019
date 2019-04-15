package com.tuhj.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

import com.tuhj.myrule.MySelfRule;

@SpringBootApplication
@EnableEurekaClient  // 表明这个服务是一个eureka的客户端
/**
 * 注解作用：
 * 	在启动该微服务的时候，就能去加载我们自定义的Ribbon配置类，从而是配置生效
 * 注意事项：
 * 	自定义的这个 MySelfRule 不能和主启动类在同一个包或子包下面
 * 原因：
 * 	主启动类上有 @SpringBootApplication 注解，而 @SpringBootApplication 中包含 @ComponentScan，
 * 	官方给出明确警告：这个自定义配置类不能放在 @ComponentScan 扫描的当前包或子包下面，否则，
 * 	我们自定义的这个配置类，就会被所有Ribbon客户端共享，达不到特殊化定制的目的
 */
@RibbonClient(name = "CLOUD-DEPT", configuration = MySelfRule.class)
public class DeptConsumer80_App {

	public static void main(String[] args) {
		SpringApplication.run(DeptConsumer80_App.class, args);
	}
}
