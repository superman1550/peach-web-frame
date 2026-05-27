package cn.jpeach.frame.core;

import cn.jpeach.frame.comp.PxSideMenu;
import cn.jpeach.frame.entity.DocumentElement;

class DesignWidgetSideMenu extends DesignWidget<PxSideMenu> {
	@Override
	String getTag() {
		return "PxSideMenu";
	}

	@Override
	PxSideMenu createComponent(WebForm form, DocumentElement element) {
		PxSideMenu panel = new PxSideMenu(form);
		this.init(panel, element);
		return panel;
	}

	@Override
	boolean hasChild() {
		return false;
	}

	void init(PxSideMenu comp, DocumentElement element) {
		super.init(comp, element);
//		this.setIntProperty(comp, element, FrameConstant.PRO_WIDTH, 0);
//		this.setStrProperty(comp, element, FrameConstant.PRO_TITLE, "");
//		this.setBoolProperty(comp, element, FrameConstant.PRO_COLLAPSED, false);
	}
}
