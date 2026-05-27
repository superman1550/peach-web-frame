package cn.jpeach.frame.core;

import cn.jpeach.frame.entity.DocumentElement;

abstract class DesignWidgetFormComp<T extends WebWidget> extends DesignWidget<T> {
	void init(T comp, DocumentElement element) {
		super.init(comp, element);
		this.setIntProperty(comp, element, Constant.PRO_WIDTH, false, 0);
		this.setIntProperty(comp, element, Constant.PRO_HEIGHT, false, 0);
		this.setBoolProperty(comp, element, Constant.PRO_DISABLED, false);
		this.setBoolProperty(comp, element, Constant.PRO_READONLY, false);
	}
}
