package com.example.demo;



import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.properties.MysqlProperties;

@RestController
public class TestSome {
	
	@Resource
	private MysqlProperties myProperties;
	
	@RequestMapping("/showPros")
	public Object showPros() {
		System.out.println(myProperties);
		return  myProperties;
	}

}
