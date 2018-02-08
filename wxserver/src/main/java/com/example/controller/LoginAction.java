package com.example.controller;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.example.common.StaticValues;
import com.example.properties.AppProperties;
import com.example.properties.InterfaceUrl;
import com.example.util.Coder;
import com.example.util.HttpAndRestTemplateUtils;

@Controller
@RequestMapping("/login")
public class LoginAction {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private AppProperties appPros; 
	
	@Autowired
	private InterfaceUrl interfaceUrl; 
	
	private static String access_token;
	
	private static String open_id;
	
	
	@ResponseBody
	@RequestMapping("/ydConnect")
	public Object  ydConnect(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException, Exception {
		String app_grant_type = request.getParameter("app_grant_type");//固定值
		String state = request.getParameter("state");//一个随机数
		String sig = request.getParameter("sig");//请求串的签名，以appkey作为密钥
		//通过app_grant_type的值来判断是什么请求
		if(StaticValues.WEB_FIRST.equals(app_grant_type)) {//web第一次请求
			String code = request.getParameter("code");//由教育云平台产生。注意此code会在10分钟内过期。
			//先校验
			StringBuffer param = new StringBuffer();
			param.append("app_grant_type=").append(app_grant_type);
			param.append("code=").append(code);
			param.append("state=").append(state);
			String mySig  =  new BigInteger(Coder.encryptHMAC((param.toString()).getBytes("utf-8"), appPros.getKey())).toString();
			if(!mySig.equals(sig)) {//说明不是移动访问我的应用
				return "the request not come from ChinaMobile!";
			}
			//调用接口获取Access Token和open_id
			String url = interfaceUrl.getPrefix()+interfaceUrl.getOauth();
			Map<String,String> paramMap = new HashMap<>();
			paramMap.put("grant_type", "authorization_code");
			paramMap.put("client_id", appPros.getId());
			paramMap.put("code", code);
			Integer myState=new Random().nextInt(10000);
			paramMap.put("state", myState+"");
			String mySig2 = new BigInteger(Coder.encryptHMAC((parseMap2String(paramMap)).getBytes("utf-8"), appPros.getKey())).toString();
			paramMap.put("sig", mySig2);
			String newUrl = HttpAndRestTemplateUtils.appendUrlAndParam(url,paramMap);
			restTemplate.getForObject(newUrl, String.class);
		}else if(StaticValues.TOCKEN_RETURN.equals(app_grant_type)) {//返回tocken
			access_token = request.getParameter("access_token");//授权令牌
			open_id = request.getParameter("open_id");//用户绑定的open_id
			String scope = request.getParameter("scope");//权限信息用逗号进行分隔，详见3.2OpenApi
			String expires_in = request.getParameter("expires_in");//该access token的有效期，单位为秒
			if(Integer.parseInt(expires_in)<0) {//access_token过期了，需要重新获取
				
			}
			
		}
		return "param is null";
	}
	
	public static String  parseMap2String(Map<String,String> paramMap) {
		List<String> list = new ArrayList<>();
		Set<String> keys = paramMap.keySet();
		for (String key : keys) {
			String keyValue = key+"="+paramMap.get(key);
			list.add(keyValue);
		}
		Collections.sort(list);
		StringBuffer sb = new StringBuffer();
		for (String string : list) {
			sb.append(string);
		}
		return sb.toString();
	}

}

