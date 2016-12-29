package com.sn.utils.base;

import java.io.InputStreamReader;
import java.util.Properties;

/**
 * 配置文件读取工具
 */
public class PropertyUtil {

	/**
	 * 获取配置属性
	 * 
	 * @param key
	 * @param resourcePath
	 * @return
	 */
	public final static String getProperty(String key, String resourcePath) {
		Properties p = new Properties();
		try {
			p.load(PropertyUtil.class.getClassLoader().getResourceAsStream(
					resourcePath));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return p.getProperty(key);
	}

	/**
	 * 获取配置属性
	 * 
	 * @param key
	 * @param resourcePath
	 * @param charset
	 * @return
	 */
	public final static String getProperty(String key, String resourcePath,
			String charset) {
		Properties p = new Properties();
		try {
			p.load(new InputStreamReader(PropertyUtil.class.getClassLoader()
					.getResourceAsStream(resourcePath), charset));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return p.getProperty(key);
	}
}
