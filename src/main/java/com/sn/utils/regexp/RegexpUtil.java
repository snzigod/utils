package com.sn.utils.regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则校验工具
 */
public class RegexpUtil {

	/**
	 * 正则校验
	 * 
	 * @param s
	 * @param p
	 * @return
	 */
	public static boolean regexp(String s, String p) {
		Pattern pattern = Pattern.compile(p);
		Matcher matcher = pattern.matcher(s);
		return matcher.matches();
	}
}