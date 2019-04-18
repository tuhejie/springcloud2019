package com.tuhj.cloud.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.tuhj.cloud.entities.Dept;

//@FeignClient(value = "CLOUD-DEPT")
@FeignClient(value = "CLOUD-DEPT", fallbackFactory = DeptClientServiceFallbackFactory.class)
public interface DeptClientService {

	@GetMapping(value = "/dept/get/{id}")
	public Dept get(@PathVariable("id") long id);

	@GetMapping(value = "/dept/list")
	public List<Dept> list();

	@PostMapping(value = "/dept/add")
	public boolean add(Dept dept);
}
