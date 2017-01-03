package com.sn.utils.string;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 反射工具类
 */
public class ReflectUtil {

	/**
	 * 反射调用指定构造方法创建对象
	 * 
	 * @param clazz
	 * @param argTypes
	 * @param args
	 * @return
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public static <T> T invokeConstructor(Class<T> clazz, Class<?>[] argTypes,
			Object[] args) throws NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		Constructor<T> constructor = clazz.getConstructor(argTypes);
		return constructor.newInstance(args);
	}

	/**
	 * 反射调用指定对象属性的getter方法
	 * 
	 * @param target
	 * @param fieldName
	 * @return
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public static <T> Object invokeGetter(T target, String fieldName)
			throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {
		// 如果属性名为xxx，则方法名为getXxx
		String methodName = "get" + StringUtil.getFirstCharUpperCase(fieldName);
		Method method = target.getClass().getMethod(methodName);
		return method.invoke(target);
	}

	/**
	 * 反射调用指定对象属性的setter方法
	 * 
	 * @param target
	 * @param fieldName
	 * @param args
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public static <T> void invokeSetter(T target, String fieldName, Object args)
			throws NoSuchFieldException, SecurityException,
			NoSuchMethodException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		// 如果属性名为xxx，则方法名为setXxx
		String methodName = "set" + StringUtil.getFirstCharUpperCase(fieldName);
		Class<?> clazz = target.getClass();
		Field field = clazz.getDeclaredField(fieldName);
		Method method = clazz.getMethod(methodName, field.getType());
		method.invoke(target, args);
	}
}
