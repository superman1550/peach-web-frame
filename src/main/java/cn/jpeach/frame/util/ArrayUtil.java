package cn.jpeach.frame.util;

public class ArrayUtil {
	public static Object[] merge(Object[] arg1, Object[] arg2) {
		Object[] objects = new Object[arg1.length + arg2.length];
		System.arraycopy(arg1, 0, objects, 0, arg1.length);
		System.arraycopy(arg2, 0, objects, arg1.length, arg2.length);
		return objects;
	}

	public static String[] toString(Object[] objects) {
		if (objects == null) {
			return null;
		}
		String[] strArray = new String[objects.length];
		for (int i = 0; i < objects.length; i++) {
			strArray[i] = String.valueOf(objects[i]);
		}
		return strArray;
	}
}
