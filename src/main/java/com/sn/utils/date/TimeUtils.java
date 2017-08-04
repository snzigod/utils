package com.sn.utils.date;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 时间处理工具
 * 
 * @author gxzhou2
 *
 */
public class TimeUtils {

	/**
	 * 时光穿梭机
	 * 
	 * @author gxzhou2
	 *
	 */
	public static class TimeShuttles {

		/**
		 * 时光穿梭类型
		 * 
		 * @author gxzhou2
		 *
		 */
		public enum TimeShuttleType {
			/**
			 * 日
			 */
			DAY,
			/**
			 * 月
			 */
			MONTH,
			/**
			 * 年
			 */
			YEAR,
			/**
			 * 周
			 */
			WEEK,
			/**
			 * 季
			 */
			SEASON;
		}

		/**
		 * 获取最早时间
		 * 
		 * @param date
		 *            待穿梭时间
		 * @param timeShuttleType
		 *            时光穿梭类型
		 * @return 最早时间
		 */
		public static Date getStartTime(Date date, TimeShuttleType timeShuttleType) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.set(Calendar.HOUR_OF_DAY, calendar.getMinimum(Calendar.HOUR_OF_DAY));
			calendar.set(Calendar.MINUTE, calendar.getMinimum(Calendar.MINUTE));
			calendar.set(Calendar.SECOND, calendar.getMinimum(Calendar.SECOND));
			switch (timeShuttleType) {
			case MONTH:
				calendar.set(Calendar.DAY_OF_MONTH, calendar.getMinimum(Calendar.DAY_OF_MONTH));
				break;
			case YEAR:
				calendar.set(Calendar.DAY_OF_YEAR, calendar.getMinimum(Calendar.DAY_OF_YEAR));
				break;
			case WEEK:
				calendar.set(Calendar.DAY_OF_WEEK, calendar.getMinimum(Calendar.DAY_OF_WEEK));
				break;
			case SEASON:
				calendar.set(Calendar.MONTH, (calendar.get(Calendar.MONTH) / 3) * 3);
				calendar.set(Calendar.DAY_OF_MONTH, calendar.getMinimum(Calendar.DAY_OF_MONTH));
				break;
			default:
			}
			return calendar.getTime();
		}

		/**
		 * 获取最晚时间
		 * 
		 * @param date
		 *            待穿梭时间
		 * @param timeShuttleType
		 *            时光穿梭类型
		 * @return 最晚时间
		 */
		public static Date getEndTime(Date date, TimeShuttleType timeShuttleType) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.set(Calendar.HOUR_OF_DAY, calendar.getMaximum(Calendar.HOUR_OF_DAY));
			calendar.set(Calendar.MINUTE, calendar.getMaximum(Calendar.MINUTE));
			calendar.set(Calendar.SECOND, calendar.getMaximum(Calendar.SECOND));
			switch (timeShuttleType) {
			case MONTH:
				calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
				break;
			case YEAR:
				calendar.set(Calendar.DAY_OF_YEAR, calendar.getActualMaximum(Calendar.DAY_OF_YEAR));
				break;
			case WEEK:
				calendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
				break;
			case SEASON:
				calendar.set(Calendar.MONTH, (calendar.get(Calendar.MONTH) / 3) * 3 + 2);
				calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
				break;
			default:
			}
			return calendar.getTime();
		}

	}

	/**
	 * 时间分片器
	 * 
	 * @author gxzhou2
	 *
	 */
	public static class TimeSeparator {

		/**
		 * 时间类型
		 * 
		 * @author gxzhou2
		 *
		 */
		public enum TimeRangeType {
			/**
			 * 秒
			 */
			SECOND("yyyyMMdd HH:mm:ss"),
			/**
			 * 分
			 */
			MINUTE("yyyyMMdd HH:mm"),
			/**
			 * 时
			 */
			HOUR("yyyyMMdd HH"),
			/**
			 * 日
			 */
			DAY("yyyyMMdd"),
			/**
			 * 月
			 */
			MONTH("yyyyMM"),
			/**
			 * 年
			 */
			YEAR("yyyy");

			private String value;

			TimeRangeType(String value) {
				this.value = value;
			}

			public String getValue() {
				return value;
			}
		}

		/**
		 * 根据开始结束时间获取时间段
		 * 
		 * @param dateStart
		 *            开始时间
		 * @param dateEnd
		 *            结束时间
		 * @param timeType
		 *            时间类型
		 * @return 时间段
		 */
		public static List<Date> getTimeList(Date dateStart, Date dateEnd, TimeRangeType timeType) {
			List<Date> dateList = new ArrayList<Date>();
			dateList.add(dateStart);
			Calendar calendarStart = Calendar.getInstance();
			calendarStart.setTime(dateStart);
			Calendar calendarEnd = Calendar.getInstance();
			if (timeType == TimeRangeType.SECOND) {
				calendarEnd.setTime(dateEnd);
			} else {
				calendarEnd.setTime(dateEnd);
			}
			while (dateEnd.after(calendarStart.getTime())) {
				switch (timeType) {
				case MINUTE:
					calendarStart.add(Calendar.MINUTE, 1);
					break;
				case HOUR:
					calendarStart.add(Calendar.HOUR, 1);
					break;
				case DAY:
					calendarStart.add(Calendar.DAY_OF_MONTH, 1);
					break;
				case MONTH:
					calendarStart.add(Calendar.MONTH, 1);
					break;
				case YEAR:
					calendarStart.add(Calendar.YEAR, 1);
					break;
				default:
					calendarStart.add(Calendar.SECOND, 1);
				}
				if (calendarStart.getTime().after(calendarEnd.getTime())) {
					break;
				}
				dateList.add(calendarStart.getTime());
			}
			return dateList;
		}

	}

}
