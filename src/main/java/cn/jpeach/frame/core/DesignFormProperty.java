package cn.jpeach.frame.core;

import cn.jpeach.frame.entity.DocumentElement;

class DesignFormProperty extends DesignProperty {

	void init(WebForm form, DocumentElement element) {
		this.setIntProperty(form, element, Constant.PRO_WIDTH, true, 0);
		this.setIntProperty(form, element, Constant.PRO_HEIGHT, true, 0);
		this.setStrProperty(form, element, Constant.PRO_TITLE, false, "");
	}
}
