package com.example.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 考勤实体
 * @author hzz
 */
@Entity
@Table(name="Attendance")
public class Attendance implements Serializable{
	@Id
	@Column(name="ID",length=64)
	private  String  id;
	
	@Column(name="CARD_ID",length=128)
	private  String  cardId;//卡号
	
	@Column(name="CAPTURE_TIME",length=64)
	private  Date    captureTime;//刷卡时间
	
	@Column(name="POS_ID",length=128)
	private  String  posId;//刷卡机ID
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public Date getCaptureTime() {
		return captureTime;
	}
	public void setCaptureTime(Date captureTime) {
		this.captureTime = captureTime;
	}
	public String getPosId() {
		return posId;
	}
	public void setPosId(String posId) {
		this.posId = posId;
	}

	
}
