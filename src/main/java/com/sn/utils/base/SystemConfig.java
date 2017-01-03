package com.sn.utils.base;

import java.util.Properties;

import com.sn.utils.web.SpringContextUtil;

/**
 * 读取系统配置
 */
public class SystemConfig {
	private static Properties configProperties;

	static {
		configProperties = (Properties) SpringContextUtil
				.getBean("configProperties");
	}

	public static String getProperty(String key) {
		return configProperties.getProperty(key);
	}

	public static Properties getConfigProperties() {
		return configProperties;
	}
}
