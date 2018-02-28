package com.example.util;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.util.WebUtils;



/**
 * @(#) Spring WebUtils  扩展 WEB Attribue 和 Session处理
 *
 * @description : 
 * @author : George
 * @version: Version No.1
 * @copyright : FiberHome FHZ Telecommunication Technologies Co.Ltd.
 */
public class WebUtil extends WebUtils {
	public final static String USERSESSION = "USERSESSION";
	public final static String USERSESSIONID = "USERSESSIONID";
	public final static String USER_LOCALE = "USER_LOCALE";
	/**
	 * 得到国际化LOCAL
	 * @param request
	 * @return "zh_CN"或者"en_US"
	 */
	public static String getUserLocale(HttpServletRequest request) {
		return (String) getSessionAttribute(request, USER_LOCALE);
	}
	
	public static String getUserSessionId(HttpServletRequest request) {
		return (String) getSessionAttribute(request, USERSESSIONID);
	}

//	public static CUserInfo getUserSession(HttpServletRequest request) {
//		return (CUserInfo) getSessionAttribute(request, USERSESSION);
//	}
	
	public static void setUserCookie(HttpServletRequest request,
			HttpServletResponse response, String username, String password, String userid) {
//		int maxAge = 60 * 60 * 24 * 30;// 保存30天
//		setCookie(request, response, "USERNAME", username, maxAge);
//		setCookie(request, response, "USERID", userid, maxAge);
//		setCookie(request, response, "USERPASSWORD", password, maxAge);
	}

	public static String getUserId(HttpServletRequest request) {
		Cookie cookies[] = request.getCookies();
		try {
			if (cookies != null && cookies.length > 0) {
				for (int i = 0; i < cookies.length; i++) {
					if ("USERID".equals(cookies[i].getName())) {
						return cookies[i].getValue();
					}
				}
			}
		} catch (Exception ex) {
		}
		return "";
	}
	public static String getUserName(HttpServletRequest request) {
		Cookie cookies[] = request.getCookies();
		try {
			if (cookies != null && cookies.length > 0) {
				for (int i = 0; i < cookies.length; i++) {
					if ("USERNAME".equals(cookies[i].getName())) {
						return cookies[i].getValue();
					}
				}
			}
		} catch (Exception ex) {
		}
		return "";
	}

	public static String getUserPassword(HttpServletRequest request) {
		Cookie cookies[] = request.getCookies();
		try {
			if (cookies != null && cookies.length > 0) {
				for (int i = 0; i < cookies.length; i++) {
					if ("USERPASSWORD".equals(cookies[i].getName())) {
						return cookies[i].getValue();
					}
				}
			}
		} catch (Exception ex) {
		}
		return "";
	}

	public static Object getAndRemoveSessionAttribute(
			HttpServletRequest request, String name) {
		Object object = getSessionAttribute(request, name);
		request.getSession().removeAttribute(name);
		return object;
	}

	public static Object getAndRemoveRequiredSessionAttribute(
			HttpServletRequest request, String name) {
		Object object = getRequiredSessionAttribute(request, name);
		request.getSession().removeAttribute(name);
		return object;
	}
	
//	public static void setUserScreenLockCookie(HttpServletRequest request,
//			HttpServletResponse response, String screenLockTime) {
//		int maxAge = 60 * 60 * 24 * 30;// 保存30天
//		CUserInfo cust = (CUserInfo)request.getSession().getAttribute(WebUtil.USERSESSION);
//		if(cust != null){
//			String userId = cust.getCustId();
//			setCookie(request, response, userId, screenLockTime, maxAge);
//		}
//	}
//	
//	public static String getUserScreenLockTime(HttpServletRequest request) {
//		Cookie cookies[] = request.getCookies();
//		CUserInfo cust = (CUserInfo)request.getSession().getAttribute(WebUtil.USERSESSION);
//		if(cust != null){
//			String userId = cust.getCustId();
//			try {
//				if (cookies != null && cookies.length > 0) {
//					for (int i = 0; i < cookies.length; i++) {
//						if (userId.equals(cookies[i].getName())) {
//							return cookies[i].getValue();
//						}
//					}
//				}
//			} catch (Exception ex) {
//			}
//		}
//		return null;
//	}

	public static void setCookie(HttpServletRequest request,
			HttpServletResponse response, String name, String value, int maxAge) {
		Cookie cookie = new Cookie(name, value);
		cookie.setMaxAge(maxAge);
		String serverName = request.getServerName();
		String domain = getDomainOfServerName(serverName);
		if (domain != null && domain.indexOf('.') != -1) {
			cookie.setDomain('.' + domain);
		}
		cookie.setPath("/");
		response.addCookie(cookie);
	}

	public static String getDomainOfServerName(String host) {
		if (IPAddressUtil.isIpAddress(host))
			return null;
		String[] names = host.split(".");
		int len = names.length;
		if (len >= 2)
			return names[len - 2] + '.' + names[len - 1];
		return host;
	}    
	/**
	 * 获取IP地址
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) { 
	       String ip = request.getHeader("x-forwarded-for"); 
	       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	           ip = request.getHeader("Proxy-Client-IP"); 
	       } 
	       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	           ip = request.getHeader("WL-Proxy-Client-IP"); 
	       } 
	       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	           ip = request.getRemoteAddr(); 
	       } 
	       return ip; 
	} 

}

