package com.example.util;


import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

/**
 * HTTP����ķ�װ
 * һ��java����http�����ַ�ʽ
 *  	1.HttpURLConnectionʵ��
 * 		2.HttpClientʵ��
 * 		3.Spring��RestTemplate
 * 			�ù������װ����RestTemplate����post��Get����
 * @author Hzz
 * @date 2017-08-23
 *
 */
public class HttpAndRestTemplateUtils {
	
	
	/**
	 * ��װ׼������post����,����Я����½��Ϣ
	 * @param object
	 * @return
	 */
	public static HttpEntity<?> getFormEntityWithCookie(Map param,String jsessionId,String  token,Map headersMap){
		 HttpHeaders headers = new HttpHeaders();
	     headers.add("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.87 Safari/537.36");
	     Set<String>  headerSet = headersMap.keySet();
	     for (String string : headerSet) {
	    	 headers.add(string, headersMap.get(string)+"");
		}
	     List<String> cookies = new ArrayList<>();
	     if(jsessionId!=null){
	    	 cookies.add("JSESSIONID=" + jsessionId);
	     }
	     if(token!=null){
	    	 cookies.add("token=" + token); 
	     }
	     headers.put(HttpHeaders.COOKIE,cookies);
	     MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
	     Set<String>  set =  param.keySet();
	     for (String string : set) {
			map.add(string,param.get(string)+"");
		}
	     HttpEntity<MultiValueMap<String, String>> formEntity = new HttpEntity<MultiValueMap<String, String>>(map, headers);
	     return formEntity;
	     //ResponseEntity<String> response = restTemplate.postForEntity(url, formEntity, String.class);
	}
	
	/**
	 * post���ύ
	 * @param param
	 * @return
	 */
	public static HttpEntity<?>  getFormEntity(Map<String,String> param){
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("Accept-Charset","utf-8");
	    headers.add("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.87 Safari/537.36");
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        Set<String>  set =  param.keySet();
	     for (String string : set) {
			map.add(string,param.get(string)+"");
		}
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<MultiValueMap<String, String>>(map, headers);
        return  entity;
        //ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
	}
	
	
	public static HttpEntity<?>  getFormEntity2(Map<String,String> param){
		HttpHeaders headers = new HttpHeaders();
	     MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
	     headers.setContentType(type);
	     headers.add("Accept", MediaType.APPLICATION_JSON.toString());
	    headers.add("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.87 Safari/537.36");
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        Set<String>  set =  param.keySet();
	     for (String string : set) {
			map.add(string,param.get(string)+"");
		}
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<MultiValueMap<String, String>>(map, headers);
        return  entity;
        //ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
	}
	
	
	
	/**
	 * ��װ׼������post���󣬲�����Object
	 * @param object
	 * @return
	 */
	public static HttpEntity<String> getFormEntity(Object object){
		 HttpHeaders headers = new HttpHeaders();
	     MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
	     headers.setContentType(type);
	     headers.add("Accept", MediaType.APPLICATION_JSON.toString());
	     Object obj = JSONObject.toJSON(object);
	     HttpEntity<String> formEntity = new HttpEntity<String>(obj.toString(), headers);
	     return formEntity;
	}
	
	/**
	 * ׼��get�����url,Map��������Ĳ���
	 * @param url
	 * @param map
	 * @return
	 */
	public static String appendUrlAndParam(String url,Map<String,String> map){
		String newUrl = url + "?";
		Set<String>  set = map.keySet();
		for (String key : set) {
			newUrl = newUrl + key +"="+ map.get(key) + "&"	;	
		}
		if(set.size()>0){
			newUrl = newUrl.substring(0, newUrl.length()-1);
		}
		return newUrl;
	}
	
	/**
	 * ׼��get�����url,Object��������Ĳ���
	 * @param url
	 * @param obj
	 * @return
	 */
	public static String appendUrlAndObject(String url,Object obj){
		//������ת��map
		Map<String,String> map = JSONObject.parseObject(JSONObject.toJSONString(obj).toString(), new TypeReference<Map<String,String>>(){});
		return appendUrlAndParam(url,map);
	}
	
	/**
	 * �ܷ����url
	 */
	public static boolean canConnectUrl(String url){
		try {
			URL ur=new URL(url);
			return canConnectIp(ur.getHost());
		} catch (MalformedURLException e) {
			return false;
		}
	}
	/**
	 * �ܷ�������ip
	 */
	public static boolean canConnectIp(String ip){
		boolean tong = false;
		try {
			InetAddress address = InetAddress.getByName(ip);
			tong = address.isReachable(2000);
		} catch (Exception ex) {
			tong = false;
		}
		return tong;
	}
	
	/**
	 * ��װ׼������post����,����Я����½��Ϣ
	 * @param object
	 * @return
	 */
	public static HttpEntity<?> getFormEntityWithCookie2(Map param,Map headersMap){
		 HttpHeaders headers = new HttpHeaders();
	     headers.add("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.86 Safari/537.36");
	     Set<String>  headerSet = headersMap.keySet();
	     for (String string : headerSet) {
	    	 headers.add(string, headersMap.get(string)+"");
		}
	     MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
	     Set<String>  set =  param.keySet();
	     for (String string : set) {
			map.add(string,param.get(string)+"");
		}
	     HttpEntity<MultiValueMap<String, String>> formEntity = new HttpEntity<MultiValueMap<String, String>>(map, headers);
	     return formEntity;
	     //ResponseEntity<String> response = restTemplate.postForEntity(url, formEntity, String.class);
	}
	
	/************************************************************************************
	 * ����Я��cookie
	 * 
	 *  HttpHeaders headers = new HttpHeaders();
        List<String> cookies = new ArrayList<>();
        cookies.add("JSESSIONID=" + Strings.nullToEmpty(jsessionId));
        cookies.add("token=" + Strings.nullToEmpty(token));
        headers.put(HttpHeaders.COOKIE,cookies);
        HttpEntity request = new HttpEntity(null, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
	 * 
	 * 
	 * post��
	 * 
	 * HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("title", title);
        map.add("desc", desc);
        map.add("userid", toUserId);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
	 * 
	 * 
	 * post json
	 * 
	 * HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Lists.newArrayList(MediaType.APPLICATION_JSON));
        Object requestJson = JSONObject.toJSON(object);
        HttpEntity<String> entity = new HttpEntity<String>(requestJson, headers);
        ResponseEntity<String> resp = restTemplate.postForEntity(url,entity,String.class);
	 * 
	 *
	 * 
	 * ����ͼƬ
	 * 
	 * 	HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM));
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		ResponseEntity<byte[]> response = restTemplate.exchange(url,HttpMethod.GET, entity, byte[].class);
		byte[] imageBytes = response.getBody();
	 * 
	 *************************************************************************************/
	
	

}
