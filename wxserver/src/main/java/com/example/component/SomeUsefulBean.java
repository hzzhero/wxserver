package com.example.component;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class SomeUsefulBean {
	
	@Bean
	public  RestTemplate  getRestTemplate(){
		SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();  
		requestFactory.setConnectTimeout(1500);// 设置超时  
		requestFactory.setReadTimeout(1500);  
		return new  RestTemplate(requestFactory);
	}

}
