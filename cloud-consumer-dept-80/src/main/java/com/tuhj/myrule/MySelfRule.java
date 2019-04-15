package com.tuhj.myrule;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;

@Configuration
public class MySelfRule {
	
	@Bean
	public IRule myRule() {
		// Ribbon 默认为轮询，我自定义为随机
		return new RandomRule();
	}

}
