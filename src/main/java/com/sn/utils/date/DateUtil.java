package com.sn.utils.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期处理工具
 */
public abstract class DateUtil {

	/**
	 * 日期字符串转换为日期
	 * 
	 * @param dateStr
	 * @param format
	 * @return
	 */
	public static Date parseDate(String dateStr, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			return sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 格式化日期为日期字符串
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String formatDate(Date date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	/**
	 * 格式化日期字符串
	 * 
	 * @param dateStr
	 * @param oldFormat
	 * @param newFormat
	 * @return
	 */
	public static String formatDate(String dateStr, String oldFormat,
			String newFormat) {
		return formatDate(parseDate(dateStr, oldFormat), newFormat);
	}

	/**
	 * 获取数天前的日期
	 * 
	 * @param date
	 * @param days
	 * @return
	 */
	public static Date getDateBeforeDays(Date date, int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH)
				- days);
		return calendar.getTime();
	}

	/**
	 * 获取数天后的日期
	 * 
	 * @param date
	 * @param days
	 * @return
	 */
	public static Date getDateAfterDays(Date date, int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH)
				+ days);
		return calendar.getTime();
	}

	/**
	 * 获取两个日期字符串相隔的天数
	 * 
	 * @param dateStr1
	 * @param dateStr2
	 * @return
	 */
	public static Integer getDaysBetweenDates(String dateStr1, String dateStr2,
			String format) {
		return (int) ((TimeMillisUtil.getTimeMillis(dateStr1, format) - TimeMillisUtil
				.getTimeMillis(dateStr2, format)) / 1000 / 60 / 60 / 24);
	}

	/**
	 * 获取两个日期相隔的天数
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static Integer getDaysBetweenDates(Date date1, Date date2) {
		return (int) ((TimeMillisUtil.getTimeMillis(date1) - TimeMillisUtil
				.getTimeMillis(date2)) / 1000 / 60 / 60 / 24);
	}

	private static String weekdays[] = { "星期日", "星期一", "星期二", "星期三", "星期四",
			"星期五", "星期六" };

	/**
	 * 将日期转化为星期
	 * 
	 * @param date
	 * @return
	 */
	public static String getWeekday(Date date) {
		Calendar c = Calendar.getInstance();// 获得一个日历的实例
		c.setTime(date);
		return weekdays[c.get(Calendar.DAY_OF_WEEK) - 1];
	}

	/**
	 * 获取数天前的星期
	 * 
	 * @param date
	 * @param days
	 * @return
	 */
	public static String getWeekdayBeforeDays(Date date, int days) {
		Calendar c = Calendar.getInstance();// 获得一个日历的实例
		c.setTime(getDateBeforeDays(date, days));
		return weekdays[c.get(Calendar.DAY_OF_WEEK) - 1];
	}

	/**
	 * 获取数天后的星期
	 * 
	 * @param date
	 * @param days
	 * @return
	 */
	public static String getWeekdayAfterDays(Date date, int days) {
		Calendar c = Calendar.getInstance();// 获得一个日历的实例
		c.setTime(getDateAfterDays(date, days));
		return weekdays[c.get(Calendar.DAY_OF_WEEK) - 1];
	}

	/**
	 * 根据生日获取年龄
	 * 
	 * @param birthday
	 * @return
	 */
	public static Integer getAgeFromBirthday(Date birthday) {
		return Integer.parseInt(formatDate(new Date(), DateFormat.YYYY))
				- Integer.parseInt(formatDate(birthday, DateFormat.YYYY));
	}

	/**
	 * 根据身份证号码获取生日
	 * 
	 * @param IDCardNo
	 * @return
	 */
	public static Date getBirthdayFromIDCardNo(String IDCardNo) {
		return parseDate(IDCardNo.substring(6, 14), DateFormat.YYYYMMDD);
	}

	/**
	 * 根据身份证号码获取年龄
	 * 
	 * @param IDCardNo
	 * @return
	 */
	public static Integer getAgeFromIDCardNo(String IDCardNo) {
		return Integer.parseInt(formatDate(new Date(), DateFormat.YYYY))
				- Integer.parseInt(formatDate(
						getBirthdayFromIDCardNo(IDCardNo), DateFormat.YYYY));
	}

}
