package com.example.util;


import java.security.MessageDigest;

/**
 * @�?）MD5Encode.java
 * 
 * @description: MD5加密
 * @author: heshan 2012/07/05
 * @version: Version No.
 * @modify: MODIFIER'S NAME YYYY/MM/DD
 * @Copyright: 烽火众智
 */
public class MD5Encode {

	/**
	 * 加密字符�?
	 * 
	 * @param str
	 *            �?��密的字符�?
	 * @return 加密后的字符�?
	 */
	public static String encode(String str) {

		try {
			MessageDigest md = MessageDigest.getInstance("MD5");

			md.update(str.getBytes("UTF-8"));

			byte[] hash = md.digest();
			StringBuffer hexString = new StringBuffer();
			for (int i = 0; i < hash.length; i++) {
				if ((0xff & hash[i]) < 0x10) {
					hexString.append("0"
							+ Integer.toHexString((0xFF & hash[i])));
				} else {
					hexString.append(Integer.toHexString(0xFF & hash[i]));
				}
			}
			return hexString.toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return null;
	}

	public  static void main(String[] args) {
		
		System.out.println(encode("1234"));
	}
}
