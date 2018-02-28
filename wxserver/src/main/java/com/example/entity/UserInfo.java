package com.example.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;

public class UserInfo implements Serializable{
	
	private String custId;
	private String custName;
	private String custPhone;
	private String realName;
	private String onlineState;//在线状态
	private String lastRegisterIp;//IP
	private Date lastRegisterTime;//时间
	
	public UserInfo() {}
	
	
	public UserInfo(String custId, String custName, String realName, String onlineState, String lastRegisterIp,
			Date lastRegisterTime, String policeCode, String idNumber) {
		super();
		this.custId = custId;
		this.custName = custName;
		this.realName = realName;
		this.onlineState = onlineState;
		this.lastRegisterIp = lastRegisterIp;
		this.lastRegisterTime = lastRegisterTime;
	}



	@Id
	@Column(name = "CUST_ID", unique = true, nullable = false, length = 48)
	public String getCustId() {
		return this.custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}



	@Column(name = "CUST_NAME", length = 48)
	public String getCustName() {
		return this.custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	@Column(name = "REAL_NAME", length = 48)
	public String getRealName() {
		return this.realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	@Column(name = "CUST_PHONE", length = 24)
	public String getCustPhone() {
		return this.custPhone;
	}

	public void setCustPhone(String custPhone) {
		this.custPhone = custPhone;
	}

	




	@Column(name = "ONLINE_STATE", length = 3)
	public String getOnlineState() {
		return this.onlineState;
	}

	public void setOnlineState(String onlineState) {
		this.onlineState = onlineState;
	}




	@Column(name = "LAST_REGISTER_IP", length = 48)
	public String getLastRegisterIp() {
		return this.lastRegisterIp;
	}

	public void setLastRegisterIp(String lastRegisterIp) {
		this.lastRegisterIp = lastRegisterIp;
	}

	@Column(name = "LAST_REGISTER_TIME", length = 7)
	public Date getLastRegisterTime() {
		return this.lastRegisterTime;
	}

	public void setLastRegisterTime(Date lastRegisterTime) {
		this.lastRegisterTime = lastRegisterTime;
	}



}
