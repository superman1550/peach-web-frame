package cn.jpeach.frame.dao;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import cn.jpeach.frame.core.WebForm;
import cn.jpeach.frame.entity.DocumentElement;

public final class StaticDaoDataCache {
	private static final Map<String, DaoModel> global = new ConcurrentHashMap<String, DaoModel>();
	private static final Map<String, Map<String, DaoModel>> local = new ConcurrentHashMap<String, Map<String, DaoModel>>();

	public static void create(WebForm form, DocumentElement child) {
		String cls = form.getClass().getName();
		DaoModel model = StaticDaoModelBuild.build(child);
		if (local.containsKey(cls)) {
			local.get(cls).put(model.getName(), model);
		} else {
			Map<String, DaoModel> cache = new ConcurrentHashMap<String, DaoModel>();
			cache.put(model.getName(), model);
			local.put(cls, cache);
		}
	}

	static DaoModel getDaoModel(String name) {
		return global.get(name);
	}

	static DaoModel getDaoModel(WebForm form, String name) {
		String cls = form.getClass().getName();
		if (local.containsKey(cls)) {
			Map<String, DaoModel> cache = local.get(cls);
			if (cache.containsKey(name)) {
				return cache.get(name);
			}
		}
		return getDaoModel(name);
	}
}
