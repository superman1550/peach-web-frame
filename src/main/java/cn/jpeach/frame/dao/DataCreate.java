package cn.jpeach.frame.dao;

import cn.jpeach.frame.core.WebForm;
import cn.jpeach.frame.core.WebObject;
import cn.jpeach.frame.exception.FrameException;

public final class DataCreate extends WebObject {
	public static DataDao getDataDao(WebForm form, String daoId) {
		BaseDao baseDao = getFormDao(form, daoId);
		if (baseDao == null) {
			DaoModel model = StaticDaoDataCache.getDaoModel(form, daoId);
			if (model == null) {
				throw new FrameException(String.format("未找到ID为[%s]的dao配置", daoId));
			}
			DataDao dao = new DataDao(model);
			WebObject.regFormDao(form, daoId, dao);
			return dao;
		} else {
			return (DataDao) baseDao;
		}
	}
}
