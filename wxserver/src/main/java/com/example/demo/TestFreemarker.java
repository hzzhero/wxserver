package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestFreemarker {
	
	@RequestMapping("/freemarker")
	public  ModelAndView  freemarker() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("message", "testFreemarker");
		mav.setViewName("freemarker");
		return mav;
	}

}
