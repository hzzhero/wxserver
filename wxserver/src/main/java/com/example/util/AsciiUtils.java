package com.example.util;



/**
 *@FileName : (AsciiUtils.java) 
 *
 *@description : ASCII工具�?
 *@author : Zhihao.Du  
 *@version : Version No.1
 *@create : 2017�?�?�?下午3:30:46
 *@modify : 2017�?�?�?下午3:30:46
 *@copyright : FiberHome FHZ Telecommunication Technologies Co.Ltd.
 */
public class AsciiUtils {
	
	/**
	 * 控制字符正则
	 */
	public static final String ISOControlReg="[\\x00-\\x20,\\x7F]";
	
	/**
	 * @Description : 移除控制字符
	 * @param value
	 * @return
	 */
	public static String removeISOControlChar(String value){
		return value.replaceAll(ISOControlReg, "");
	}
	
}
