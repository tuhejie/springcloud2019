package com.tuhj.cloud.cfgbeans;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ConfigBean { // @Configuration配置的 ConfigBean 等同于 applicationContext.xml

	@Bean
	/**
	 * Spring Cloud Ribbon 是基于 Netflix Ribbon 实现的一套 客户端 负载均工具， （添加 @LoadBalanced
	 * 注解后，能直接实现客户端的负载均衡）
	 */
	@LoadBalanced
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

}
