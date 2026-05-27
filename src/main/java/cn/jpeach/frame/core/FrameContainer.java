package cn.jpeach.frame.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.jpeach.frame.conf.ConfigCache;
import cn.jpeach.frame.exception.ReflectorException;

public final class FrameContainer {
	private static final Logger logger = LoggerFactory.getLogger(FrameContainer.class);

	public static void init() {
		initMain();
	}

	private static void initMain() {
		String fullClassName = ConfigCache.getApplicationConifg().getMainClass();
		try {
			Class<FrameWindow> parentClass = FrameWindow.class;
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
			logger.info("加载入口类：{}", fullClassName);
		} catch (ClassNotFoundException e) {
			throw new ReflectorException("加载入口类失败！", e);
		}
	}
}
