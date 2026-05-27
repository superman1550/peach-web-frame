package cn.jpeach.frame.core;

import cn.jpeach.frame.dao.BaseDao;

public class WebObject {
	protected static BaseDao getFormDao(WebForm form, String daoId) {
		return form.daoCache.get(daoId);
	}

	protected  static void regFormDao(WebForm form, String daoId, BaseDao dao) {
		form.daoCache.put(daoId, dao);
	}
}
