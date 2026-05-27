package cn.jpeach.frame.core;

import cn.jpeach.frame.comp.PxSwitchButton;
import cn.jpeach.frame.entity.DocumentElement;

class DesignWidgetSwitchButton extends DesignWidget<PxSwitchButton> {

	@Override
	String getTag() {
		return "PxSwitchButton";
	}

	@Override
	PxSwitchButton createComponent(WebForm form, DocumentElement element) {
		PxSwitchButton panel = new PxSwitchButton(form);
		this.init(panel, element);
		return panel;
	}

	@Override
	boolean hasChild() {
		return false;
	}

	void init(PxSwitchButton comp, DocumentElement element) {
		super.init(comp, element);
//		this.setIntProperty(comp, element, FrameConstant.PRO_WIDTH, 0);
//		this.setStrProperty(comp, element, FrameConstant.PRO_TITLE, "");
//		this.setBoolProperty(comp, element, FrameConstant.PRO_COLLAPSED, false);
	}
}
