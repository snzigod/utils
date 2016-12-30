package com.sn.utils.string;

import static com.sn.utils.base.Emptys.EMPTY_STR;

/**
 * 字符串处理工具
 */
public abstract class StringUtil {

	/**
	 * 字符串为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return str == null || str.length() <= 0;
	}

	/**
	 * 字符串不为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	/**
	 * 所有字符为空
	 * 
	 * @param strs
	 * @return
	 */
	public static boolean isAllEmpty(String... strs) {
		if (strs == null) {
			return true;
		}
		for (String str : strs) {
			if (isNotEmpty(str)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 任意字符为空
	 * 
	 * @param strs
	 * @return
	 */
	public static boolean isAnyEmpty(String... strs) {
		if (strs == null) {
			return true;
		}
		for (String str : strs) {
			if (isEmpty(str)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 字符串为空字符
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isBlank(String str) {
		int length = str.length();
		if (str == null || length == 0) {
			return true;
		}
		for (int i = 0; i < length; i++) {
			if (!Character.isWhitespace(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 字符串不为空字符
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNotBlank(String str) {
		int length = str.length();
		if (str == null || length == 0) {
			return false;
		}
		for (int i = 0; i < length; i++) {
			if (!Character.isWhitespace(str.charAt(i))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 所有字符串为空字符
	 * 
	 * @param strs
	 * @return
	 */
	public static boolean isAllBlank(String... strs) {
		if (strs == null) {
			return true;
		}
		for (String str : strs) {
			if (isNotBlank(str)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 任意字符串为空字符
	 * 
	 * @param strs
	 * @return
	 */
	public static boolean isAnyBlank(String... strs) {
		if (strs == null) {
			return true;
		}
		for (String str : strs) {
			if (isBlank(str)) {
				return true;
			}
		}
		return false;
	}

	public static String defaultIfNull(String str) {
		return (str == null) ? EMPTY_STR : str;
	}

	public static String defaultIfNull(String str, String defaultStr) {
		return (str == null) ? defaultStr : str;
	}

	public static String defaultIfEmpty(String str) {
		return isEmpty(str) ? EMPTY_STR : str;
	}

	public static String defaultIfEmpty(String str, String defaultStr) {
		return isEmpty(str) ? defaultStr : str;
	}

	public static String defaultIfBlank(String str) {
		return isBlank(str) ? EMPTY_STR : str;
	}

	public static String defaultIfBlank(String str, String defaultStr) {
		return isBlank(str) ? defaultStr : str;
	}
}
