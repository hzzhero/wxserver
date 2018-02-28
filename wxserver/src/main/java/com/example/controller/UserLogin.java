package com.example.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.example.common.StaticValues;
import com.example.entity.UserInfo;
import com.example.service.UserService;
import com.example.util.WebUtil;



/**
 * ********************************************************************
 * @FileName : (UserLogin.java)
 * 
 * @description : 用户登录处理类
 * @author : 张葳 
 * @version : Version No.1
 * @create : 2015-9-10 下午12:58:10
 * @modify : 2015-9-10 下午12:58:10
 * @copyright : FiberHome FHZ Telecommunication Technologies Co.Ltd.
 *********************************************************************
 */
public class UserLogin {
	
	
	private static UserLogin instFlg = null;
	//是否允许并发登录
	private Boolean synLoginFlg = false;
	
	private Map<String, Map<String,Object>> userNameMap = new HashMap<String, Map<String,Object>>(); //存放用户名以及用户的一些信息
	private Map<String, Map<String,Object>> sessionIdMap = new HashMap<String, Map<String,Object>>(); //存放sessionId以及用户的一些信息
	//key:userId value:sessionId:用于给指定用户推送信息
	public  Map<String,String> userIdAndSessionIdMap = new HashMap<String, String>();	
	public  Map<String,String> sessionIdAndIPMap = new HashMap<String, String>();	
	//账号后台登录
	public  Map<String,String> manageUserMap = new HashMap<String, String>();	
	public  final String USER_INFO = "USER_INFO";//用户信息key
	public  final String SESSION = "SESSION";//session key
	public  final String LASTBEATTIME = "LASTBEATTIME";//最后一次心跳时间 key
	public  final String NOTEXIST_TIME = "NOTEXIST_TIME";//超时未收到心跳的次数 key
	private static UserService userService;
	
	/**
	 * 记录Session
	 */
	private HashMap<String, HttpSession> sessionCache = null;
//	//对象锁
//	private volatile boolean flag = false;
//	//定时线程
//	private static CheckBeat checkThrad = null;
//	
//	private final long expireTime = 60*1000;
    
	public UserLogin() {
		sessionCache = new HashMap<String, HttpSession>();
	}
	
	@SuppressWarnings("static-access")
	public UserLogin(UserService userService) {
		this.userService = userService;
		
	}
	// 单例模式 只生成一个UserLogin 
	public static synchronized UserLogin singleton() {
		if (instFlg == null) {
			instFlg = new UserLogin();
//			//启动用户在线状态管理器
//			checkThrad = instFlg.new CheckBeat();
//			new Timer().schedule(checkThrad, 15000, 1000 * 20);
		}
		return instFlg;
	}
	
//	/**
//	 * 初始化
//	 * 
//	 */
//	private synchronized void initialize() {
//		userNameMap = Collections.synchronizedMap(new HashMap<String, Map>());
//		sessionIdMap = Collections.synchronizedMap(new HashMap<String, Map>());
//		userIdAndSessionIdMap = Collections.synchronizedMap(new HashMap<String, String>());
//	}
//	
	/**
	 * 判断相同用户名用户是否已经登录过
	 * @param userName
	 * @return
	 */
	public synchronized boolean checkLoginByName(String userName) {
		return userNameMap.containsKey(userName);
	}
	
	/**
	 * 判断sessionID是否已经登录过
	 * @param sessionId
	 * @return
	 */
	public synchronized boolean checkLoginBySessionId(String sessionId) {
		return sessionIdMap.containsKey(sessionId);
	}
	
	
	/**
	 * 用户登录:允许并发登录（待加入用户最大并发数控制）
	 * @param userinfo
	 */
	public synchronized Integer login(String sessionId, UserInfo userInfo,
			HttpSession session,String ip) {
		
		//不允许并发登录
		if(false == synLoginFlg && sessionIdMap.get(sessionId) == null){
			session.setAttribute("user", userInfo);
			Map<String,Object> map = new HashMap<String,Object>();
			map.put(USER_INFO, userInfo);
			map.put(LASTBEATTIME, new Date());
			map.put(NOTEXIST_TIME, 0);
			map.put(SESSION, session);
			
			userNameMap.put(userInfo.getCustName(), map);
			sessionIdMap.put(sessionId, map);
			userIdAndSessionIdMap.put(userInfo.getCustId(), sessionId);
			sessionIdAndIPMap.put(sessionId, ip);
			cacheSession(sessionId, session);
			
			return StaticValues.LOGIN_SUCCESS;
		}
		return StaticValues.LOGIN_FAILED;
	}

	/**
	 * 用户登录:接收用户心跳
	 * @param sessionId
	 */
	public synchronized Integer receiveBeat(String sessionId) {
		Map<String,Object> map = sessionIdMap.get(sessionId);
		if(null == map) {
			return StaticValues.LOGIN_BEAT_ERROR;
		} else {
			UserInfo cuser = (UserInfo) map.get("USER_INFO");
			map.put(LASTBEATTIME, new Date());
			return StaticValues.LOGIN_SUCCESS;				
		}
	}
	
	/**
	 * 获取用户信息
	 * @param sessionId
	 * @return
	 */
	public UserInfo getUser(String sessionId) {
		UserInfo user = null;
		if(null != sessionIdMap.get(sessionId)){
			user = (UserInfo) sessionIdMap.get(sessionId).get(USER_INFO);
		}
		return user;
	}
	
	/**
	 * 获取用户ID
	 * @param sessionId
	 * @return
	 */
	public String getUserId(String sessionId) {
		try {
			UserInfo user = (UserInfo) sessionIdMap.get(sessionId).get(USER_INFO);
			return user.getCustId();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("session is not exist! sessionId=" + sessionId);
		}
		return null;
	}
	
	/**
	 * 获取所有的sessionMap
	 * @return
	 */
	public Map<String,Map<String,Object>> getSessionMap() {
		return sessionIdMap;
	}
	
	public void cacheSession(String key, HttpSession session) {
		if(sessionCache.containsKey(key))
			return;
		else
			sessionCache.put(key, session);
	}
	
	public void exceptionDestroySession(String sessionId) {
		HttpSession session = sessionCache.get(sessionId);
		if(null == session)
			return;
		sessionCache.remove(sessionId);
		
		// session销毁
		session.removeAttribute(WebUtil.USERSESSION);
		session.removeAttribute(WebUtil.USERSESSIONID);
		session.invalidate();
	}
	
	/**
	 * 用户退出:session退出
	 * 
	 * @param sessionId
	 */
	public synchronized Integer logout(String sessionId) {
		try {
			UserInfo info = (UserInfo) sessionIdMap.get(sessionId).get(USER_INFO);
			userNameMap.remove(info.getCustName());
			userIdAndSessionIdMap.remove(info.getCustId());
			manageUserMap.remove(info.getCustId());
			sessionIdAndIPMap.remove(sessionId);
			info = null;
			sessionIdMap.remove(sessionId);
			return StaticValues.LOGIN_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("loginout exception sessionId=【" + sessionId + "】");
			return StaticValues.LOGINOUT_FAIL;
		}
	}
	
	/**
	 * 用户登出：session和业务
	 * @param sessionId
	 * @return
	 */
	public LoginResponse loginOut(String sessionId,String reason) {
		
		UserInfo user = UserLogin.singleton().getUser(sessionId);
		
		//记录登录日志
		return userService.loginOut(sessionId);
	}


}