 package com.tuhj.cloud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.tuhj.cloud.entities.Dept;

@RestController
public class DeptController_Consumer {

//	private static final String REST_URL_PREFIX = "http://localhost:8001";
	// 根据微服务的名称访问
	private static final String REST_URL_PREFIX = "http://CLOUD-DEPT";

	/**
	 * 使用 restTemplate 访问 restful 接口非常简单粗暴
	 */
	@Autowired
	private RestTemplate restTemplate;

	@PostMapping(value = "/consumer/dept/add")
	public boolean add(@RequestBody Dept dept) {
		return restTemplate.postForObject(REST_URL_PREFIX + "/dept/add", dept, Boolean.class);
	}

	@GetMapping(value = "/consumer/dept/get/{id}")
	public Dept get(@PathVariable("id") Long id) {
		return restTemplate.getForObject(REST_URL_PREFIX + "/dept/get/" + id, Dept.class);
	}

	@GetMapping(value = "/consumer/dept/list")
	public List<Dept> list() {
		return restTemplate.getForObject(REST_URL_PREFIX + "/dept/list", List.class);
	}

	@GetMapping(value = "/consumer/dept/discovery")
	public Object discovery() {
//		return restTemplate.getForObject(REST_URL_PREFIX + "/dept/discovery", Object.class);
		
		Object object = restTemplate.getForObject(REST_URL_PREFIX + "/dept/discovery", Object.class);
		DiscoveryClient client = (DiscoveryClient)object;
		List<String> list = client.getServices();
		System.out.println("**********" + list);

		List<ServiceInstance> srvList = client.getInstances("CLOUD-DEPT");
		for (ServiceInstance element : srvList) {
			System.out.println(element.getServiceId() + "\t" + element.getHost() + "\t" + element.getPort() + "\t"
					+ element.getUri());
		}
		return object;
	}
}
