package com.sn.utils.string;

import java.util.UUID;

/**
 * UUID工具集
 */
public abstract class UUIDUtil {

	/**
	 * 获取UUID
	 * 
	 * @return
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}
}
