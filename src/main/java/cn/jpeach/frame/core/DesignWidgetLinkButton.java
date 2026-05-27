package cn.jpeach.frame.core;

import org.apache.commons.lang3.StringUtils;

import cn.jpeach.frame.comp.PxLinkButton;
import cn.jpeach.frame.entity.DocumentElement;

class DesignWidgetLinkButton extends DesignWidget<PxLinkButton> {

	@Override
	String getTag() {
		return "PxLinkButton";
	}

	@Override
	PxLinkButton createComponent(WebForm form, DocumentElement element) {
		PxLinkButton panel = new PxLinkButton(form);
		this.init(panel, element);
		return panel;
	}

	@Override
	boolean hasChild() {
		return false;
	}

	void init(PxLinkButton comp, DocumentElement element) {
		super.init(comp, element);
		this.setIntProperty(comp, element, Constant.PRO_WIDTH, false, 0);
		this.setIntProperty(comp, element, Constant.PRO_HEIGHT, false, 0);
		this.setBoolProperty(comp, element, Constant.PRO_DISABLED, false);
		this.setBoolProperty(comp, element, Constant.PRO_TOGGLE, false);
		this.setBoolProperty(comp, element, Constant.PRO_SELECTED, false);
		this.setStrProperty(comp, element, Constant.PRO_GROUP);
		this.setBoolProperty(comp, element, Constant.PRO_PLAIN, false);
		this.setStrProperty(comp, element, Constant.PRO_TEXT, StringUtils.EMPTY);
		this.setStrProperty(comp, element, Constant.PRO_ICONCLS, StringUtils.EMPTY);
	}
}
