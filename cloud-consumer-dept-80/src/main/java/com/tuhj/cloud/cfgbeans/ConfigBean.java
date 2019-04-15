package com.tuhj.cloud.cfgbeans;

import java.util.Random;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.netflix.loadbalancer.AvailabilityFilteringRule;
import com.netflix.loadbalancer.BestAvailableRule;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RetryRule;
import com.netflix.loadbalancer.RoundRobinRule;
import com.netflix.loadbalancer.WeightedResponseTimeRule;
import com.netflix.loadbalancer.ZoneAvoidanceRule;

@Configuration
public class ConfigBean { // @Configuration配置的 ConfigBean 等同于 applicationContext.xml

	@Bean
	@LoadBalanced // Spring Cloud Ribbon 是基于 Netflix Ribbon 实现的一套 客户端 负载均工具（添加 @LoadBalanced
					// 注解后，能直接实现客户端的负载均衡）
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	/**
	 * 根据返回不同的对象，定义不同的负载均衡算法
	 * @return
	 */
	@Bean
	public IRule myRule() {
		// 1.轮询
		return new RoundRobinRule();
		
		// 2.随机
		// return new RandomRule();
		
		// 3.会先过滤掉由于多次访问故障而处于断路器跳闸状态的服务，还有并发的连接数量超过阈值的服务，然后对剩余的服务列表按照轮询策略进行访问
		// return new AvailabilityFilteringRule();
		
		// 4.根据平均响应时间计算所有服务的权重，响应时间越快的服务权重越大，被选中的概率越高。刚启动时如果统计信息不足，则使用RoundRobinRule策略，等统计信息足够，会切换到WeightedResponseTimeRule
		// return new WeightedResponseTimeRule();
		
		// 5.达到的目的，用我们重新选择的随机算法替代默认的轮询
		// return new RetryRule();
		
		// 6.会过滤掉由于多次访问故障而处于跳闸状态的服务，然后选择一个并发量最小的服务
		// return new BestAvailableRule();
		
		// 7.默认规则，复合判断server所在区域的性能和server的可用性选择服务器
		// return new ZoneAvoidanceRule();
	}

}
