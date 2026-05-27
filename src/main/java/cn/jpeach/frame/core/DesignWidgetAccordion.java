package cn.jpeach.frame.core;

import cn.jpeach.frame.comp.PxAccordion;
import cn.jpeach.frame.entity.DocumentElement;

class DesignWidgetAccordion extends DesignWidget<PxAccordion> {
	@Override
	String getTag() {
		return "PxAccordion";
	}

	@Override
	PxAccordion createComponent(WebForm form, DocumentElement element) {
		PxAccordion accordion = new PxAccordion(form);
		this.init(accordion, element);
		return accordion;
	}

	@Override
	boolean hasChild() {
		return true;
	}

	void init(PxAccordion comp, DocumentElement element) {
		super.init(comp, element);
		this.setIntProperty(comp, element, Constant.PRO_WIDTH, 160);
		this.setIntProperty(comp, element, Constant.PRO_HEIGHT, 160);
		this.setBoolProperty(comp, element, Constant.PRO_FIT, false);
		this.setBoolProperty(comp, element, Constant.PRO_BORDER, true);
		this.setBoolProperty(comp, element, Constant.PRO_ANIMATE, true);
		this.setBoolProperty(comp, element, Constant.PRO_MULTIPLE, false);
		this.setIntProperty(comp, element, Constant.PRO_SELECTED, 0);

	}
}
