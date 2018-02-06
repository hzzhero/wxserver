package com.example.util;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils extends org.springframework.util.StringUtils {
	public static boolean isNullOREmpty(String args) {
		if (args == null || args.trim().equals("")) {
			return true;
		}
		return false;
	}

	public static boolean isEmail(String email) {
		if(email == null){
			return false;
		}
		Pattern regex = Pattern
				.compile("^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+$");
		Matcher matcher = regex.matcher(email);
		if (matcher.matches()) {
			return true;
		}
		return false;
	}

	/**
	 * 判断是否是字母和数字的结�?
	 * 
	 * @param name
	 * @return
	 */
	public static boolean isAsciiOrDigit(String name) {
		for (int i = 0; i < name.length(); i++) {
			char ch = name.charAt(i);
			if (!isAscii(ch))
				return false;
		}
		return true;
	}

	public static boolean isDigit(String name) {
		for (int i = 0; i < name.length(); i++) {
			char ch = name.charAt(i);
			if (!isDigit(ch))
				return false;
		}
		return true;
	}

	public static boolean isAscii(char ch) {
		return (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')
				|| (ch >= '0' && ch <= '9');
	}

	public static boolean isDigit(char ch) {
		return (ch >= '0' && ch <= '9');
	}

	/**
	 * 获取字符长度，一个汉字作�? 1 个字�?, �?个英文字母作�? 0.5 个字�?
	 * 
	 * @param text
	 * @return 字符长度，如：text="中国",返回 2；text="test",返回 2；text="中国ABC",返回 4.
	 */
	public static int getLength(String text) {
		int textLength = text.length();
		int length = textLength;
		for (int i = 0; i < textLength; i++) {
			if (String.valueOf(text.charAt(i)).getBytes().length > 1) {
				length++;
			}
		}
		return (length % 2 == 0) ? length / 2 : length / 2 + 1;
	}

	public static boolean suffixValidate(String fileName, String[] suffixs) {
		int i = fileName.lastIndexOf(".");
		if (i != -1) {
			String extend = fileName.substring(i).toUpperCase();
			StringBuffer suffixsBf = new StringBuffer();
			for (int j = 0; j < suffixs.length; j++) {
				suffixsBf.append(suffixs[j]);
			}
			if (suffixsBf.toString().toUpperCase().indexOf(extend) != -1) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static void main(String[] args) {
		System.out.println(isEmail(null));
	}
}
