package cn.jpeach.frame.core;

import cn.jpeach.frame.comp.PxLayout;
import cn.jpeach.frame.entity.DocumentElement;

class DesignWidgetLayout extends DesignWidget<PxLayout> {
	@Override
	String getTag() {
		return "PxLayout";
	}

	@Override
	PxLayout createComponent(WebForm form, DocumentElement element) {
		PxLayout panel = new PxLayout(form);
		this.init(panel, element);
		return panel;
	}

	@Override
	boolean hasChild() {
		return true;
	}

	void init(PxLayout comp, DocumentElement element) {
		super.init(comp, element);
		this.setIntProperty(comp, element, Constant.PRO_WIDTH, false, 0);
		this.setIntProperty(comp, element, Constant.PRO_HEIGHT, false, 0);
		this.setStrProperty(comp, element, Constant.PRO_WIDTH_UNIT, "px");
		this.setStrProperty(comp, element, Constant.PRO_HEIGHT_UNIT, "px");
		this.setStrProperty(comp, element, Constant.PRO_CLS, PxLayout.CLS_DEFAULT);
	}
}
