package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableHystrixDashboard//开启图像化的注解
public class DeptConsumer_DashBoard_App {

	public static void main(String[] args) {
		
		SpringApplication.run(DeptConsumer_DashBoard_App.class, args);
	}

}
