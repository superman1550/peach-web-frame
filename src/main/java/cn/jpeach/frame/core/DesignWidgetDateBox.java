package cn.jpeach.frame.core;

import cn.jpeach.frame.comp.PxDateBox;
import cn.jpeach.frame.entity.DocumentElement;

class DesignWidgetDateBox extends DesignWidgetFormComp<PxDateBox> {

	@Override
	String getTag() {
		return "PxDateBox";
	}

	@Override
	PxDateBox createComponent(WebForm form, DocumentElement element) {
		PxDateBox panel = new PxDateBox(form);
		this.init(panel, element);
		return panel;
	}

	@Override
	boolean hasChild() {
		return false;
	}

	void init(PxDateBox comp, DocumentElement element) {
		super.init(comp, element);
		this.setStrProperty(comp, element, Constant.PRO_TYPE, "date");
	}
}
