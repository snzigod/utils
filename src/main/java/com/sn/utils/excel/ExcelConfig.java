package com.sn.utils.excel;

import com.sn.utils.base.SystemConfig;

/**
 * excel工具
 */
public class ExcelConfig {
	private final static String DEFAULT_FILE_NAME = "temp.xlsx";// 默认文件名
	private final static String DEFAULT_FILE_SUFFIX = ".xlsx";

	private final static String KEY_FILE_NAME = "excel.file.name";// excel文件名
	private final static String KEY_HAS_TITLE = "excel.hasTitle";// excel是否存在标题
	private final static String KEY_FIELD_NAEMS_EN = "excel.field.names.en";// excel英文字段名
	private final static String KEY_FIELD_NAEMS_CN = "excel.field.names.cn";// excel中文字段名
	private final static String KEY_FIELD_NOT_NULLS = "excel.field.not.nulls";// excel非空字段
	private final static String KEY_FIELD_ERROR_FLAG = "excel.field.error.flag";// excel字段错误标志
	private final static String KEY_FIELD_REGEXP_PREFIX = "excel.field.regexp.prefix";// excel字段正则校验前缀

	protected final static String FILE_NAME = BaseUtils.isEmpty(SystemConfig.getProperty(KEY_FILE_NAME)) ? DEFAULT_FILE_NAME : SystemConfig.getProperty(KEY_FILE_NAME);
	protected final static String EXCEL_SUFFIX = FILE_NAME.indexOf(".") == FILE_NAME.length() - 1 ? DEFAULT_FILE_SUFFIX : FILE_NAME.substring(FILE_NAME.indexOf("."), FILE_NAME.length());
	protected final static boolean HAS_TITLE = BaseUtils.isEmpty(SystemConfig.getProperty(KEY_HAS_TITLE)) ? false : new Boolean(SystemConfig.getProperty(KEY_HAS_TITLE));
	protected final static String[] FIELD_NAEMS_EN = SystemConfig.getProperty(KEY_FIELD_NAEMS_EN).split(",");
	protected final static String[] FIELD_NAEMS_CN = BaseUtils.unicode2String(SystemConfig.getProperty(KEY_FIELD_NAEMS_CN)).split(",");
	protected final static String[] FIELD_NOT_NULLS = SystemConfig.getProperty(KEY_FIELD_NOT_NULLS).split(",");
	protected final static String ERROR_FLAG = SystemConfig.getProperty(KEY_FIELD_ERROR_FLAG);
	protected final static String REGEXP_PREFIX = SystemConfig.getProperty(KEY_FIELD_REGEXP_PREFIX);

	protected final static String DEFAULT_DATE_FAMAT = "yyyy/MM/dd";
	
	protected final static String READ_FAIL_MSG = "行读取失败。";
	protected final static String CHECK_NULL_MSG = "不能为空。";
	protected final static String CHECK_FAIL_MSG = "校验失败。";

}
