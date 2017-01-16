package com.sn.utils.excel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sn.utils.base.SystemConfig;

/**
 * 基础工具
 */
public class BaseUtils {

	private final static String DEFAULT_UNICODE_SPLIT = ",";

	private final static String KEY_UNICODE_SPLIT = "unicode.split";// 中文unicode分隔

	private final static String UNICODE_SPLIT = isEmpty(SystemConfig.getProperty(KEY_UNICODE_SPLIT)) ? DEFAULT_UNICODE_SPLIT : SystemConfig.getProperty(KEY_UNICODE_SPLIT);// 默认unicode前缀

	/**
	 * unicode转字符串
	 * @param unicode
	 * @return
	 */
	public static String unicode2String(String unicode) {
		StringBuffer sb = new StringBuffer();
		String[] hex = unicode.split(UNICODE_SPLIT);
		for (int i = 0; i < hex.length; i++) {
			// 转换出每一个代码点
			int data = Integer.parseInt(hex[i], 16);
			// 追加成string
			sb.append((char) data);
		}
		return sb.toString();
	}

	/**
	 * 正则校验
	 * @param s
	 * @param p
	 * @return
	 */
	public static boolean regexp(String s, String p) {
		Pattern pattern = Pattern.compile(p);
		Matcher matcher = pattern.matcher(s);
		return matcher.matches();
	}

	/**
	 * 判断存在字空符串
	 * @param s
	 * @return
	 */
	public static boolean isEmpty(String s) {
		return (s == null || "".equals(s)) ? true : false;
	}

	/**
	 * 判断存在字空符串
	 * @param params
	 * @return
	 */
	public static boolean isEmpty(String... params) {
		if (params == null || params.length <= 0) {
			return true;
		}
		for (String param : params) {
			if (isEmpty(param)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 字符串转换
	 * @param s
	 * @return
	 */
	public static String toString(String s) {
		return s == null ? "" : s;
	}
	
	/**
	 * 日期格式化
	 * @param date
	 * @param format
	 * @return
	 */
	public static String formatDate(Date date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}
}
