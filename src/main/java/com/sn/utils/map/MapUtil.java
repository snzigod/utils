package com.sn.utils.map;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * map工具
 */
public class MapUtil {

	/**
	 * map转object
	 * 
	 * @param map
	 * @param beanClass
	 * @return
	 * @throws Exception
	 */
	public static Object map2Object(Map<String, Object> map, Class<?> beanClass)
			throws Exception {
		if (map == null) {
			return null;
		}
		Object obj = beanClass.newInstance();
		Field[] fields = obj.getClass().getDeclaredFields();
		for (Field field : fields) {
			int mod = field.getModifiers();
			if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
				continue;
			}
			field.setAccessible(true);
			field.set(obj, map.get(field.getName()));
		}
		return obj;
	}

	/**
	 * object转map
	 * 
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Object> object2Map(Object obj) throws Exception {
		if (obj == null) {
			return null;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		Field[] declaredFields = obj.getClass().getDeclaredFields();
		for (Field field : declaredFields) {
			field.setAccessible(true);
			if (field.get(obj) == null) {
				break;
			}
			map.put(field.getName(), field.get(obj));
		}
		return map;
	}

	/**
	 * map排序
	 * 
	 * @param params
	 * @return
	 */
	public static Map<String, Object> sortMapByKey(Map<String, Object> params) {
		if (params == null || params.isEmpty()) {
			return null;
		}
		Map<String, Object> sortMap = new TreeMap<String, Object>(
				new Comparator<String>() {
					public int compare(String o1, String o2) {
						return o1.compareTo(o2);
					}
				});
		sortMap.putAll(params);
		return sortMap;
	}
}
