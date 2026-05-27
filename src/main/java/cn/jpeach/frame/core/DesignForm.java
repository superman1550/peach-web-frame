package cn.jpeach.frame.core;

import java.util.HashMap;
import java.util.Map;

import cn.jpeach.frame.conf.ConfigCache;
import cn.jpeach.frame.entity.DocumentElement;
import cn.jpeach.frame.util.Reflector;

class DesignForm {
	private static Map<String, DocumentElement> elemCache = new HashMap<String, DocumentElement>();
	private static final String DATASOURCE = "DATASOURCE";

	static WebForm create(Class<? extends WebForm> cls) {
		WebForm form = Reflector.newInstance(cls);
		initXml(form);
		initEle(form);
		initCom(form);
		return form;
	}

	private static void initXml(WebForm form) {
		String cls = form.getClass().getName();
		if (!elemCache.containsKey(cls) || ConfigCache.isDev()) {
			elemCache.put(cls, DesignFormClass.readFromPackage(form.getClass()));
		}
	}

	private static void initEle(WebForm form) {
		DocumentElement element = elemCache.get(form.getClass().getName());
		DesignFormProperty designFormProperty = new DesignFormProperty();
		designFormProperty.init(form, element);
		for (int i = 0; i < element.childrenCount(); i++) {
			if (DATASOURCE.equalsIgnoreCase(element.getChild(i).getName())) {
				DesignSource.create(form, element.getChild(i));
			} else {
				DesignFormComponent.create(form, form, element.getChild(i));
			}
		}
	}

	private static void initCom(WebForm form) {
		form.initComponent();
		form.initEvent();
	}
}
