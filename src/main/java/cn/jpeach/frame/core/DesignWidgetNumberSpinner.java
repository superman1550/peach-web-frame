package cn.jpeach.frame.core;

import cn.jpeach.frame.comp.PxNumberSpinner;
import cn.jpeach.frame.entity.DocumentElement;

class DesignWidgetNumberSpinner extends DesignWidget<PxNumberSpinner> {
	@Override
	String getTag() {
		return "PxNumberSpinner";
	}

	@Override
	PxNumberSpinner createComponent(WebForm form, DocumentElement element) {
		PxNumberSpinner panel = new PxNumberSpinner(form);
		this.init(panel, element);
		return panel;
	}

	@Override
	boolean hasChild() {
		return false;
	}

	void init(PxNumberSpinner comp, DocumentElement element) {
		super.init(comp, element);
//		this.setIntProperty(comp, element, FrameConstant.PRO_WIDTH, 0);
//		this.setStrProperty(comp, element, FrameConstant.PRO_TITLE, "");
//		this.setBoolProperty(comp, element, FrameConstant.PRO_COLLAPSED, false);
	}
}
