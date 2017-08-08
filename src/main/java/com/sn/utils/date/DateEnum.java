package com.sn.utils.date;

/**
 * 时间枚举
 */
public interface DateEnum {

	/**
	 * 时间格式枚举
	 */
	enum DateFormat implements DateEnum {
		/**
		 * 年
		 */
		YYYY("yyyy", "yyyy", "yyyy年"),
		/**
		 * 年月
		 */
		YYYYMM("yyyyMM", "yyyy-MM-dd", "yyyy年MM月dd日"),
		/**
		 * 年月日
		 */
		YYYYMMDD("yyyyMMdd", "yyyy-MM-dd HH", "yyyy年MM月dd日HH时"),
		/**
		 * 年月日时
		 */
		YYYYMMDDHH24("yyyyMMddHH", "yyyy-MM-dd HH:mm", "yyyy年MM月dd日HH时mm分"),
		/**
		 * 年月日时分
		 */
		YYYYMMDDHH24MM("yyyyMMddHHmm", "yyyy-MM-dd HH:mm:ss", "yyyy年MM月dd日HH时mm分ss秒"),
		/**
		 * 年月日时分秒
		 */
		YYYYMMDDHH24MMSS("yyyyMMddHHmmss", "", ""),
		/**
		 * 时
		 */
		HH24("HH", "HH", "HH时"),
		/**
		 * 时分
		 */
		HH24MM("HHmm", "HH:mm", "HH时mm分"),
		/**
		 * 时分秒
		 */
		HH24MMSS("HHmmss", "HH:mm:ss", "HH时mm分ss秒");

		private String format;
		private String formatEN;
		private String formatCN;

		public String getFormat() {
			return format;
		}

		public String getFormatEN() {
			return formatEN;
		}

		public String getFormatCN() {
			return formatCN;
		}

		DateFormat(String format, String formatEN, String formatCN) {
			this.format = format;
			this.formatEN = formatEN;
			this.formatCN = formatCN;
		}
	}
}
