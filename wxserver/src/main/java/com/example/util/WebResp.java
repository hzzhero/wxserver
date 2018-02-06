package com.example.util;


/**
 * @FileName : (WebResult.java)
 * 
 * @description : Ajax 请求 Resp 封装实体
 * @author : XX
 * @version : Version No.1
 * @create : 2015-5-25 下午04:07:23
 * @modify : 2015-5-25 下午04:07:23
 * @copyright : FiberHome FHZ Telecommunication Technologies Co.Ltd.
 */
public class WebResp {
	
	private String errorCode;   //������

	private Object resp;        //���

	private int status;         //״̬��

	public WebResp() {
		// TODO Auto-generated constructor stub
	}

	public WebResp(String errorCode, Object resp, int status) {
		super();
		this.errorCode = errorCode;
		this.resp = resp;
		this.status = status;
	}

	public WebResp(String errorCode, int status) {
		super();
		this.errorCode = errorCode;
		this.status = status;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public Object getResp() {
		return resp;
	}

	public void setResp(Object resp) {
		this.resp = resp;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
