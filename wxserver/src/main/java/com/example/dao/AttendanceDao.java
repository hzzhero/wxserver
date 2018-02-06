package com.example.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.entity.Attendance;

/**
 * 继承JpaSpecificationExecutor就可以支持动态sql,用于拼接SQL
 * @author hzz
 *
 */
@Repository
public interface AttendanceDao  extends JpaRepository<Attendance, String>,JpaSpecificationExecutor<Attendance>{

	/**
	 * 默认用的是HQL
	 */
	@Query("select  a from  Attendance a  where a.captureTime between  str_to_date(?1, '%Y-%m-%d %H')   and  str_to_date(?2, '%Y-%m-%d %H')  ")
	public List<Attendance> findByTime(String start,String end);
	
	/**
	 * 可以使用native SQL
	 */
	@Query(value="select  *  from  Attendance  order by RAND() limit  ?1 ",nativeQuery=true)
	public List<Attendance> randomList(Integer n);
}
