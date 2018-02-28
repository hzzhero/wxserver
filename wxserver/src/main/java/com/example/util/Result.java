package com.example.util;

/**
 * @describe : 返回消息的封装
 * @author : mrlans
 * @create date : 2012-8-1 下午01:52:16
 * @version 1.0
 * @copyright : FiberHome FHZ Telecommunication Technologies Co.Ltd.
 */

public class Result implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -854806853174477185L;

	public final static boolean STATE_SUCCESS = true;

	public final static boolean STATE_FAILURE = false;

	public final static String INFO_SUCCESS = "方法调用成功";

	public final static String INFO_FAILURE = "方法调用失败";

	private boolean value = STATE_SUCCESS; // 正常返回true，出现错误false

	private String info = INFO_SUCCESS; // 异常返回的错误信息

	private int errorCode; // 错误码

	private Object response = null; // 返回值 返回的结果

	public Result()
	{
		super();
	}
	
	public Result(boolean value) 
	{
		super();
		this.value = value;
	}
	
	public Result(boolean value, String info) {
		super();
		this.value = value;
		this.info = info;
	}

	public Result(boolean value, String info, Object response) {
		super();
		this.value = value;
		this.info = info;
		this.response = response;
	}

	public Result(boolean value, String info, int errorCode, Object response) {
		super();
		this.value = value;
		this.info = info;
		this.errorCode = errorCode;
		this.response = response;
	}

	public boolean isValue() {
		return value;
	}

	public void setValue(boolean value) {
		this.value = value;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public Object getResponse() {
		return response;
	}

	public void setResponse(Object response) {
		this.response = response;
	}

}
