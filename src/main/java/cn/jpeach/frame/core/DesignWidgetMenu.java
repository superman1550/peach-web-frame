package cn.jpeach.frame.core;

import cn.jpeach.frame.comp.PxMenu;
import cn.jpeach.frame.entity.DocumentElement;

class DesignWidgetMenu extends DesignWidget<PxMenu> {
	@Override
	String getTag() {
		return "PxMenu";
	}

	@Override
	PxMenu createComponent(WebForm form, DocumentElement element) {
		PxMenu panel = new PxMenu(form);
		this.init(panel, element);
		return panel;
	}

	@Override
	boolean hasChild() {
		return false;
	}

	void init(PxMenu comp, DocumentElement element) {
		super.init(comp, element);
//		this.setIntProperty(comp, element, FrameConstant.PRO_WIDTH, 0);
//		this.setStrProperty(comp, element, FrameConstant.PRO_TITLE, "");
//		this.setBoolProperty(comp, element, FrameConstant.PRO_COLLAPSED, false);
	}
}
