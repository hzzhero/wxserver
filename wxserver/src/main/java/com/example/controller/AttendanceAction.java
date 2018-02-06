package com.example.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.entity.Attendance;
import com.example.service.AttendanceService;
import com.example.util.GenerateRandomStr;
import com.example.util.StringUtils;

@Controller
@RequestMapping("/attendance")
public class AttendanceAction {
	
	@Autowired
	private  AttendanceService  attendanceService ;
	
	
	@RequestMapping("/list")
	public  ModelAndView list() {
		ModelAndView mav = new ModelAndView();
//		List<Attendance> list = attendanceService.listByTime();
		List<Attendance> list = attendanceService.list();
		mav.addObject("attendances", list);
		mav.setViewName("attendanceList");
		return mav;
	}
	

	@ResponseBody
	@RequestMapping("/randomList")
	public  Object  randomList(HttpServletRequest request) {
		List<Attendance> list = attendanceService.randomList(Integer.parseInt(request.getParameter("num")));
		return list;
	}
	
	@RequestMapping("/preAdd")
	public String preAdd() {
		return "preAdd";
	}
	
	@RequestMapping("/preFind")
	public String preFind() {
		return "find";
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String  add(Attendance attendance) {
		if(StringUtils.isNullOREmpty(attendance.getId())) {
			attendance.setId(GenerateRandomStr.generateRandomString(48));
		}
		attendance.setCaptureTime(new Date());
		attendanceService.save(attendance);
		return "redirect:/attendance/list";
	}
	
	@ResponseBody
	@RequestMapping("/find")
	public Object  find(Attendance attendance) {
		List<Attendance> findAll = attendanceService.findAll(attendance);
		return  findAll;
	}

}
