package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.common.StaticValues;
import com.example.controller.LoginResponse;
import com.example.controller.UserLogin;
import com.example.dao.UserDao;
import com.example.entity.UserInfo;
import com.example.util.Result;

@Service
public class UserService {
	
	@Autowired
	private UserDao dao;
	
	public Result loginOutUpdate(UserInfo user) {
		
			return null;
	}
	
	/**
	 * 用户登出
	 * @param sessionId
	 * @return
	 */
	public LoginResponse loginOut(String sessionId) {
		LoginResponse re = new LoginResponse();
		//获取当前需要登出的用户
		UserInfo user = UserLogin.singleton().getUser(sessionId);
		if(null != user){
			user.setOnlineState(StaticValues.USER_OFFLINE.toString());
			//登出
			Integer val = UserLogin.singleton().logout(sessionId);
			if(val == StaticValues.LOGIN_SUCCESS) {
				try {
					//更新用户信息
					Result result = loginOutUpdate(user);
					if(null != result && result.isValue()){
						System.out.println("user logout success! userName=" + user.getCustName());
						re.setErrCode(StaticValues.LOGIN_SUCCESS);
						re.setSessionId(sessionId);
					}else{
						re.setErrCode(StaticValues.LOGINOUT_FAIL);
					}
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("logout error");
					re.setErrCode(StaticValues.LOGINOUT_FAIL);
				}
			}
		}else{
			System.out.println("user session already not exist! sessionId = "+sessionId);
			re.setErrCode(StaticValues.LOGIN_SUCCESS);
		}
		return re;
	}

}
