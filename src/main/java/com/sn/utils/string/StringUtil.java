package com.sn.utils.string;

import static com.sn.utils.string.Emptys.EMPTY_STR;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

	/**
	 * 将指定字符串转换成驼峰命名方式
	 * 
	 * @param str
	 * @return
	 */
	public static String toCalmelCase(String str) {
		StringBuffer buffer = new StringBuffer(str);
		if (buffer.length() > 0) {
			// 将首字母转换成小写
			char c = buffer.charAt(0);
			buffer.setCharAt(0, Character.toLowerCase(c));
			Pattern p = Pattern.compile("_\\w");
			Matcher m = p.matcher(buffer.toString());
			while (m.find()) {
				String temp = m.group(); // 匹配的字符串
				int index = buffer.indexOf(temp); // 匹配的位置
				// 去除匹配字符串中的下划线，并将剩余字符转换成大写
				buffer.replace(index, index + temp.length(),
						temp.replace("_", "").toUpperCase());
			}
		}
		return buffer.toString();
	}

	/**
	 * 将指定字符串转换成匈牙利命名方式
	 * 
	 * @param str
	 * @return
	 */
	public static String toHungarianCase(String str) {
		StringBuffer buffer = new StringBuffer(str);
		if (buffer.length() > 0) {
			Pattern p = Pattern.compile("[A-Z]");
			Matcher m = p.matcher(buffer.toString());
			while (m.find()) {
				String temp = m.group(); // 匹配的字符串
				int index = buffer.indexOf(temp); // 匹配的位置
				// 在匹配的字符串前添加下划线，并将其余字符转换成大写
				buffer.replace(index, index + temp.length(), (index > 0 ? "_"
						: "") + temp.toLowerCase());
			}
		}
		return buffer.toString();
	}

	/**
	 * 根据指定分隔符获取字符串前缀
	 * 
	 * @param str
	 * @param delim
	 * @return
	 */
	public static String getPrefix(String str, String delim) {
		String prefix = "";
		if (isNotBlank(str) && isNotBlank(delim)) {
			int pos = str.indexOf(delim);
			if (pos > 0) {
				prefix = str.substring(0, pos);
			}
		}
		return prefix;
	}

	/**
	 * 根据指定分隔符获取字符串后缀
	 * 
	 * @param str
	 * @param delim
	 * @return
	 */
	public static String getSuffix(String str, String delim) {
		String suffix = "";
		if (isNotBlank(str) && isNotBlank(delim)) {
			int pos = str.lastIndexOf(delim);
			if (pos > 0) {
				suffix = str.substring(pos + 1);
			}
		}
		return suffix;
	}

	/**
	 * 根据指定字符串和重复次数生成新字符串
	 * 
	 * @param str
	 * @param repeatCount
	 * @return
	 */
	public static String getRepeatString(String str, int repeatCount) {
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < repeatCount; i++) {
			buf.append(str);
		}
		return buf.toString();
	}

	/**
	 * 将指定字符串首字母转换成大写字母
	 * @param str
	 * @return
	 */
	public static String getFirstCharUpperCase(String str) {
		StringBuffer buffer = new StringBuffer(str);
		if (buffer.length() > 0) {
			char c = buffer.charAt(0);
			buffer.setCharAt(0, Character.toUpperCase(c));
		}
		return buffer.toString();
	}

	/**
	 * 将指定数组转换成字符串
	 * 
	 * @param objs
	 * @return
	 */
	public static String array2String(Object[] objs) {
		StringBuffer buffer = new StringBuffer();
		if (objs != null) {
			for (int i = 0; i < objs.length; i++) {
				buffer.append(objs[i]).append(",");
			}
		}
		buffer.deleteCharAt(buffer.length() - 1);
		return buffer.toString();
	}

	/**
	 * 将指定对象转换成字符串
	 * 
	 * @param obj
	 * @return
	 */
	public static String object2String(Object obj) {
		StringBuffer buffer = new StringBuffer();
		if (obj != null) {
			buffer.append(obj);
		}
		return buffer.toString();
	}
}
