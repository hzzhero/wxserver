package com.example.util;


import java.sql.SQLException;
import java.util.Random;

/**
 * @(#)GenerateRandomStr.java
 * 
 * @description : 
 * @author: heshan 2012-6-11 下午06:54:04
 * @version: Version 1.0
 * @modify: 
 * @copyright : Wuhan FiberHome Digital Technology Co.Ltd.
 */
public class GenerateRandomStr {
	
	public static Long generateRandomStr_9() {
		return generateRandomStr(9);
	}

	public static Long generateRandomStr_12() {
		return generateRandomStr(12);
	}
	
	public static Long generateRandomStr_6()
	{
		return generateRandomStr(6);
	}
	
	public static String generateRandomStr_20()
	{
		return generateRandomString(20);
	}

	/***************************************************************************
	 * 生成位数为randStrLength的随机数
	 **************************************************************************/
	public static Long generateRandomStr(int randStrLength) {
		StringBuffer generateRandStr = new StringBuffer();
		String psid;
		Random rand = new Random();
		int randNum = rand.nextInt(9) + 1;
		generateRandStr.append(randNum);
		for (int i = 1; i < randStrLength; i++) {
			randNum = rand.nextInt(10);
			generateRandStr.append(randNum);
		}
		psid = generateRandStr.toString();
		return new Long(psid);
	}
	
	public static String generateRandomString(int randStrLength) {
		StringBuffer generateRandStr = new StringBuffer();
		Random rand = new Random();
		int randNum = rand.nextInt(9) + 1;
		generateRandStr.append(randNum);
		for (int i = 1; i < randStrLength; i++) {
			randNum = rand.nextInt(10);
			generateRandStr.append(randNum);
		}
		return generateRandStr.toString();
	}
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		//System.out.println(GenerateRandomStr.generateRandomStr_20());
	}	
}
