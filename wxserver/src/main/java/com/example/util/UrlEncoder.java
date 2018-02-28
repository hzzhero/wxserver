package com.example.util;


import java.util.ArrayList;
import java.util.List;

/**
 * urlǶ�״���Ĺ�����
 * @author hzz
 *
 */
public class UrlEncoder {
	
	public static String[] picUrls=new String[]{
		"http://192.168.0.104:8080/internetdata_noxml/getPicByEncodeUrl.do",
		"http://100.61.137.1:8080/internetdata_noxml/getPicByUrl.do",
		"http://10.73.121.147:80/internetdata_noxml/getPicByUrl.do",
	};
	
	public static String[] dataUrls=new String[]{
		"http://192.168.0.104:8080/internetdata_noxml/getDataByUrl.do",
		"http://100.61.137.1:8080/internetdata_noxml/getDataByUrl.do",
		"http://10.73.121.147:80/internetdata_noxml/getDataByUrl.do",
	};
	
	
	public static String getDataUrl2HLW(String param){
		return getUrlMutiEncoded(param,"url",dataUrls);
	}
	
	public static String getPicUrl2HLW(String param){
		return getUrlMutiEncoded(param,"picUrl",picUrls);
	}
	
	public static String getUrlMutiEncoded(String urlParam,String paramKey,List<String> urls){
		StringBuffer sb = new StringBuffer();
		String myUrlParam=urlParam;
		for (String url : urls) {
			myUrlParam = url + "?" + paramKey + "=" + new String(MyBase64.encode(myUrlParam.getBytes()));
		}
		return myUrlParam;
	}
	
	
	public static String getUrlMutiEncoded(String urlParam,String paramKey,String[] urls){
		StringBuffer sb = new StringBuffer();
		String myUrlParam=urlParam;
		for (String url : urls) {
			myUrlParam = url + "?" + paramKey + "=" + new String(MyBase64.encode(myUrlParam.getBytes()));
		}
		return myUrlParam;
	}
	
	//��������
	public static void decodeUrlMuti(String urlMutiEncoded,String paramKey){
		String myurl = urlMutiEncoded;
		String paramKeyEq=paramKey+"=";
		while(true){
			if(!myurl.contains(paramKeyEq)){
				break;
			}
			myurl = myurl.substring(myurl.indexOf(paramKeyEq)+paramKeyEq.length(), myurl.length());
			myurl = new String(MyBase64.decode(myurl));
			System.out.println(myurl);
		}
		
	}
	
	public static void main(String[] args) {
		List<String> urls = new ArrayList<String>();
		urls.add("http://192.168.0.104:8080/internetdata_noxml/getPicByEncodeUrl.do");
		urls.add("http://100.61.137.1:8080/internetdata_noxml/getPicByEncodeUrl.do");
		urls.add("http://10.73.121.147:80/internetdata_noxml/getPicByEncodeUrl.do");
		String urlMutiEncoded = UrlEncoder.getUrlMutiEncoded("http://app1.showapi.com/weather/icon/day/302.png", "picUrl", urls);
		System.out.println(urlMutiEncoded);
		UrlEncoder.decodeUrlMuti(urlMutiEncoded,"picUrl");
		
		System.out.println("\n=======================================================\n");
		
		List<String> urls2 = new ArrayList<String>();
		urls2.add("http://192.168.0.104:8080/internetdata_noxml/queryTrackByTime.do");
		urls2.add("http://100.61.137.1:8080/internetdata_noxml/getDataByUrl.do");
		urls2.add("http://10.73.121.147:80/internetdata_noxml/getDataByUrl.do");
		String urlParam="{'startDate':'2018-01-03 17:20:00','endDate':'2018-01-04 19:30:00','cardId':'571899','isToday':'false','page':'2','pageSize':'200'}";
		String urlMutiEncoded2 = UrlEncoder.getUrlMutiEncoded(urlParam, "url", urls2);
		System.out.println(urlMutiEncoded2);
		UrlEncoder.decodeUrlMuti(urlMutiEncoded2,"url");
		
		String urlMutiEncoded3=getPicUrl2HLW("https://ss1.baidu.com/9vo3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=03d791b0e0c4b7452b94b116fffd1e78/58ee3d6d55fbb2fb4a944f8b444a20a44723dcef.jpg");
		System.out.println(urlMutiEncoded3);
		UrlEncoder.decodeUrlMuti(urlMutiEncoded3,"picUrl");
	}

}
