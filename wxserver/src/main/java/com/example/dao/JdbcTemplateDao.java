package com.example.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.entity.Attendance;

/**
 * spring-jdbcTemplate操作数据示例
 * @author hzz
 */
@Repository
public class JdbcTemplateDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<Attendance> findAll(){
		String sql = "select * from Attendance";
		List<Attendance> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper(Attendance.class));
		return list;
	}

}
