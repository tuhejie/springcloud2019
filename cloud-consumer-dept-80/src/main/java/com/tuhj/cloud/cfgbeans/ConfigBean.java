package com.tuhj.cloud.cfgbeans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ConfigBean { // @Configuration配置的 ConfigBean 等同于 applicationContext.xml

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

}
