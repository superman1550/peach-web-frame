package cn.jpeach.frame.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentMapCache<K, V> implements FrameCache<K, V> {
	private Map<K, V> cache = new ConcurrentHashMap<K, V>();

	@Override
	public void put(K key, V value) {
		cache.put(key, value);
	}

	@Override
	public V get(K key) {
		return cache.get(key);
	}

	@Override
	public boolean containsKey(K key) {
		return cache.containsKey(key);
	}

}
