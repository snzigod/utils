package com.sn.utils.date;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间戳处理工具
 */
public abstract class TimeMillisUtil {

	/**
	 * 获取当前时间戳
	 * 
	 * @return
	 */
	public static long getCurrentTimeMillis() {
		return System.currentTimeMillis();
	}

	/**
	 * 根据日期获取时间戳
	 * 
	 * @param date
	 * @return
	 */
	public static long getTimeMillis(Date date) {
		return date.getTime();
	}

	/**
	 * 根据日期字符串获取时间戳
	 * 
	 * @param dateStr
	 * @return
	 */
	public static long getTimeMillis(String dateStr, String format) {
		return DateUtil.parseDate(dateStr, format)
				.getTime();
	}

	/**
	 * 根据时间戳获取日期字符串
	 * 
	 * @param timeMillis
	 * @param format
	 * @return
	 */
	public static String getDateStrFromTimeMillis(long timeMillis, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(timeMillis);
	}

	/**
	 * 根据时间戳获取日期
	 * 
	 * @param timeMillis
	 * @param format
	 * @return
	 */
	public static Date getDateFromTimeMillis(long timeMillis, String format) {
		return DateUtil.parseDate(getDateStrFromTimeMillis(timeMillis, format),
				format);
	}
}
