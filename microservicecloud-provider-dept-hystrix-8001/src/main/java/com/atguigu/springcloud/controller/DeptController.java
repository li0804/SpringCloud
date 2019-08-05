package com.atguigu.springcloud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atguigu.springcloud.entites.Dept;
import com.atguigu.springcloud.service.DeptService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("dept")
public class DeptController {
	
	@Autowired
	private DeptService service;
	@Autowired
	private DiscoveryClient client;
	
	@RequestMapping("add")
	public boolean add(@RequestBody Dept dept){
		return service.add(dept);
	}
	
	@RequestMapping("get/{id}")
	//一旦调用服务方法失败并抛出错误后，会自动调用@HystrixCommand标注好的fallbackMethod调用类中指定的方法
	@HystrixCommand(fallbackMethod = "processHystrix_Get")
	public Dept get(@PathVariable("id")Long id){
		
		Dept dept = this.service.get(id);
		if(null == dept){
			throw new RuntimeException("该ID：" + id + "没有对应的信息");
		}
		
		return dept;
	}
	
	public Dept processHystrix_Get(@PathVariable("id") Long id){
		return new Dept().setDeptno(id).setDname("该ID：" + id + "没有对应的信息,null--@HystrixCommand")
				.setDb_source("no this database in MySQL");
	}
	
	@RequestMapping("list")
	public List<Dept> list(){
		return service.list();
	}
	
	@RequestMapping("discovery")
	public Object discovery(){
		
		List<String> list = client.getServices();
		System.err.println("***********"+list);
		
		List<ServiceInstance> srvList = client.getInstances("MICROSERVICECLOUD-DEPT");
		for(ServiceInstance element:srvList){
			System.err.println(element.getServiceId() + "\t" + element.getHost() + "\t" + element.getPort() + "\t" + element.getUri());
		}
		return this.client;
	}
	
}
