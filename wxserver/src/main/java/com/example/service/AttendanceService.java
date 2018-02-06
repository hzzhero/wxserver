package com.example.service;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.dao.AttendanceDao;
import com.example.entity.Attendance;
import com.example.util.StringUtils;

@Service
public class AttendanceService {
	
	@Autowired
	private AttendanceDao attendanceDao;

	public List<Attendance> list() {
		return attendanceDao.findAll();
	}
	
	public Attendance save(Attendance attendance) {
		return attendanceDao.save(attendance);
	}
	
	public List<Attendance> listByTime() {
		return attendanceDao.findByTime("2018-02-06 12:00:00","2018-02-06 14:00:00");
	}
	
	public  List<Attendance> randomList(Integer n){
		return attendanceDao.randomList(n);
	}
	
	/**
	 * 动态拼接字符串查询。
	 * @param attendance
	 * @return
	 */
	public  List<Attendance> findAll(Attendance attendance){
		 List<Attendance> findAll = attendanceDao.findAll(new Specification<Attendance>() {
			@Override
			public Predicate toPredicate(Root<Attendance> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
				Predicate predicate = cb.conjunction();
				if(!StringUtils.isNullOREmpty(attendance.getPosId())) {
					predicate.getExpressions().add(cb.like(root.get("posId"), "%"+attendance.getPosId()+"%"));
				}
				if(!StringUtils.isNullOREmpty(attendance.getCardId())) {
					predicate.getExpressions().add(cb.like(root.get("cardId"), "%"+attendance.getCardId()+"%"));
				}
				return predicate;
			}
		
		});
		 return findAll;
	}
	
}
