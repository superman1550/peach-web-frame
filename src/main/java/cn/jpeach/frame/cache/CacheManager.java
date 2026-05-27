package cn.jpeach.frame.cache;

public class CacheManager {
	public static <K, V> FrameCache<K, V> newInstance() {
		return new ConcurrentMapCache<K, V>();
	}
}
