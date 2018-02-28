package com.example.common;

import java.util.HashMap;
import java.util.Map;

public class StaticValues {
	
	public static String WEB_FIRST="receive_code";//web第一次登陆
	
	public static String TOCKEN_RETURN="receive_token";//返回tocken
	
	public static Map<String,Object>  userMap = new HashMap<>();

	public static final Integer LOGIN_SUCCESS = 0;//登录成功
	public static final Integer LOGIN_USER_NOTEXIST = 101;//用户不存在
	public static final Integer LOGIN_PASSWORD_ERROR = 102;//密码错误
	public static final Integer LOGIN_USER_ONLINE = 103;//用户已登录
	public static final Integer LOGIN_NULL_PARAM = 104;//用户名或密码为空
	public static final Integer LOGIN_FAILED = 105;//登陆服务端异常
	public static final Integer LOGINOUT_FAIL = 106;//登出失败
	public static final Integer LOGIN_BEAT_ERROR = 107;//心跳异常
	public static final Integer LOGIN_SESSION_NOTEXIST = 108;//会话不存在
	public static final Integer LOGIN_SYSFLG_ERROR = 109;//系统标识有误
	public static final Integer LOGIN_NO_GRANT = 110;//用户没有登录权限
	public static final Integer LOGIN_JINYONG = 111;//用户已经被禁用
	public static final Integer LOGIN_FAIL = 1;//登录失败 
	public static final String FOMART_PASSWORD="123456";//初始化用户密码，重置用户密码

	public static final Integer USER_ONLINE = 114;//用户在线
	public static final Integer USER_OFFLINE = 113;//用户离线
}
