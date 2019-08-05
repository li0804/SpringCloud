package com.atguigu.springcloud.dao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.atguigu.springcloud.entites.Dept;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DaoTastCaste {
	
	@Autowired
	private DeptDao deptDao;
	
	@Test
	public void findAll(){
		List<Dept> list = deptDao.findAll();
		System.err.println(list);
	}
	
	@Test
	public void addDept(){
		Dept dept = new Dept();
		dept.setDname("gna");
		deptDao.addDept(dept );
		System.err.println("ok");
	}
}
