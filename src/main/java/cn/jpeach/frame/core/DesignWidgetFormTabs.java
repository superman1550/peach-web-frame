package cn.jpeach.frame.core;

import cn.jpeach.frame.comp.PxFormTabs;
import cn.jpeach.frame.entity.DocumentElement;

class DesignWidgetFormTabs extends DesignWidget<PxFormTabs> {
	@Override
	String getTag() {
		return "PxFormTabs";
	}

	@Override
	PxFormTabs createComponent(WebForm form, DocumentElement element) {
		PxFormTabs panel = new PxFormTabs(form);
		this.init(panel, element);
		return panel;
	}

	@Override
	boolean hasChild() {
		return false;
	}

	void init(PxFormTabs comp, DocumentElement element) {
		super.init(comp, element);
		this.setBoolProperty(comp, element, Constant.PRO_FIT, false);
//		this.setIntProperty(comp, element, FrameConstant.PRO_WIDTH, 0);
//		this.setStrProperty(comp, element, FrameConstant.PRO_TITLE, "");
//		this.setBoolProperty(comp, element, FrameConstant.PRO_COLLAPSED, false);
	}
}
