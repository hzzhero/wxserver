package com.example.demo;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.util.MessageUtil;
import com.example.util.ShaUtil;

@RestController
public class WxController {
	
	@RequestMapping("/connect")
	public  Object  connect(HttpServletRequest request,HttpServletResponse response) {
		String method = request.getMethod();
		if("GET".equalsIgnoreCase(method)) {
			String signature=request.getParameter("signature");
			String timestamp=request.getParameter("timestamp");
			String nonce=request.getParameter("nonce");
			String echostr=request.getParameter("echostr");
			String tocken = "hzzhero";
			String[] strs = new String[] {tocken,timestamp,nonce};
			Arrays.sort(strs);
			StringBuffer sb = new StringBuffer();
			for (String string : strs) {
				sb.append(string);
			}
			String sha1 = ShaUtil.getSha1(sb.toString());
			if(sha1.equals(signature)) {
				Map<String, String[]> parameterMap = request.getParameterMap();
				if(!parameterMap.containsKey("openid")) {
					System.out.println("微信平台配置连接！");
				}
				Set<String> set=parameterMap.keySet();
				for (String string : set) {
					System.out.println(string+":"+parameterMap.get(string)[0]);
				}
				System.out.println(request);
				return echostr;
			}else {
				return "你是谁想模拟微信发请求！";
			}
		}else if("POST".equalsIgnoreCase(method)) {
			System.out.println("收到消息了");
			Map<String, String> xmlMap = MessageUtil.parseXml(request);
			Set<String> set = xmlMap.keySet();
			for (String string : set) {
				System.out.println(string +":"+xmlMap.get(string));
			}
			if(MessageUtil.REQ_MESSAGE_TYPE_TEXT.equals(xmlMap.get("MsgType"))) {
				TextMessage tm = new TextMessage();
				tm.setFromUserName(xmlMap.get("ToUserName"));
				tm.setToUserName(xmlMap.get("FromUserName"));
				tm.setContent("收到了你发的消息："+xmlMap.get("Content"));
				tm.setCreateTime(System.currentTimeMillis());
				tm.setMsgType(MessageUtil.REQ_MESSAGE_TYPE_TEXT);
				return MessageUtil.textMessageToXml(tm);
			}
			return null;
		}
		return null;
	}

}
