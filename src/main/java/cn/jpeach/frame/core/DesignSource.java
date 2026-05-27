package cn.jpeach.frame.core;

import cn.jpeach.frame.dao.StaticDaoDataCache;
import cn.jpeach.frame.entity.DocumentElement;

class DesignSource {
	private static final String ELEMENT_NAME_DAO = "dao";

	static void create(WebForm form, DocumentElement element) {
		for (int i = 0; i < element.childrenCount(); i++) {
			DocumentElement child = element.getChild(i);
			if (ELEMENT_NAME_DAO.equalsIgnoreCase(child.getName())) {
				StaticDaoDataCache.create(form, child);
			}
		}
	}
}
