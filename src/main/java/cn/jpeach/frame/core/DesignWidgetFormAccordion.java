package cn.jpeach.frame.core;

import cn.jpeach.frame.comp.PxFormAccordion;
import cn.jpeach.frame.entity.DocumentElement;

class DesignWidgetFormAccordion extends DesignWidget<PxFormAccordion> {
	@Override
	String getTag() {
		return "PxFormAccordion";
	}

	@Override
	PxFormAccordion createComponent(WebForm form, DocumentElement element) {
		PxFormAccordion panel = new PxFormAccordion(form);
		this.init(panel, element);
		return panel;
	}

	@Override
	boolean hasChild() {
		return false;
	}

	void init(PxFormAccordion comp, DocumentElement element) {
		super.init(comp, element);
//		this.setIntProperty(comp, element, FrameConstant.PRO_WIDTH, 0);
//		this.setStrProperty(comp, element, FrameConstant.PRO_TITLE, "");
//		this.setBoolProperty(comp, element, FrameConstant.PRO_COLLAPSED, false);
	}
}
