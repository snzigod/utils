package com.sn.utils.string;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternUtil {
		/**
		 * 验证字符串是否是email
		 * @param str
		 * @return
		 */
		public static boolean isEmail(String str) {
			Pattern pattern = Pattern.compile("[//w//.//-]+@([//w//-]+//.)+[//w//-]+", Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(str);
			return matcher.matches();
		}

		/**
		 * 验证是否是手机号码
		 * @param str
		 * @return
		 */
		public static boolean isCellphone(String str) {
			Pattern pattern = Pattern.compile("1[0-9]{10}");
			Matcher matcher = pattern.matcher(str);
			return matcher.matches();
		}

		/**
		 * 验证是否是QQ号码
		 * @param str
		 * @return
		 */
		public static boolean isQQ(String str) {
			Pattern pattern = Pattern.compile("[1-9]{5,10}");
			Matcher matcher = pattern.matcher(str);
			return matcher.matches();
		}

}