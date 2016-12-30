package com.sn.utils.string;

/**
 * 字符串处理工具
 */
public class StringUtil {

	/**
	 * 字符串空判断
	 * @param s
	 * @return
	 */
	public static boolean isEmpty(String s) {
		return s == null || s.length() <= 0;
	}
}
