package cn.jpeach.frame.core;

import cn.jpeach.frame.comp.PxCalendar;
import cn.jpeach.frame.entity.DocumentElement;

class DesignWidgetCalendar extends DesignWidget<PxCalendar> {

	@Override
	String getTag() {
		return "PxCalendar";
	}

	@Override
	PxCalendar createComponent(WebForm form, DocumentElement element) {
		PxCalendar panel = new PxCalendar(form);
		this.init(panel, element);
		return panel;
	}

	@Override
	boolean hasChild() {
		return false;
	}

	void init(PxCalendar comp, DocumentElement element) {
		super.init(comp, element);
		this.setIntProperty(comp, element, Constant.PRO_WIDTH, 250);
		this.setIntProperty(comp, element, Constant.PRO_HEIGHT, 250);
		this.setBoolProperty(comp, element, Constant.PRO_FIT, false);
		this.setBoolProperty(comp, element, Constant.PRO_BORDER, true);
	}
}
