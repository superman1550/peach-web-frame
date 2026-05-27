package cn.jpeach.frame.util;

import cn.jpeach.frame.core.WebForm;
import cn.jpeach.frame.exception.ReflectorException;

public class Reflector {

	public static Object getClassByFullName(Class<?> parentClass, String fullClassName) {
		try {
			// 1. 加载目标类
			ClassLoader cl = Thread.currentThread().getContextClassLoader();
			Class<?> targetClass = Class.forName(fullClassName, true, cl);

			// 2. 验证是否是父类的子类
			if (!parentClass.isAssignableFrom(targetClass)) {

				throw new ReflectorException(String.format("%s 不是 %s 的子类", fullClassName, parentClass.getName()));
			}

			// 3. 验证不是父类本身
			if (parentClass.equals(targetClass)) {
				throw new ReflectorException(String.format("%s 就是父类本身，请提供子类", fullClassName));
			}
			return targetClass.newInstance();
		} catch (ClassNotFoundException e) {
			throw new ReflectorException("Class Not Found", e);
		} catch (InstantiationException e) {
			throw new ReflectorException("Instantiation", e);
		} catch (IllegalAccessException e) {
			throw new ReflectorException("Illegal Access", e);
		}
	}

	public static WebForm newInstance(Class<? extends WebForm> cls) {
		if (cls == null) {
			throw new ReflectorException("Class parameter cannot be null");
		}
		if (cls.equals(WebForm.class)) {
			throw new ReflectorException(String.format("%s 就是父类本身，请提供子类", cls.getName()));
		}
		try {
			return cls.newInstance();
		} catch (InstantiationException e) {
			throw new ReflectorException("Instantiation", e);
		} catch (IllegalAccessException e) {
			throw new ReflectorException("Illegal Access", e);
		}
	}
}
