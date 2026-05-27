package cn.jpeach.frame.core;

import org.apache.commons.lang3.StringUtils;

import cn.jpeach.frame.comp.PxPanel;
import cn.jpeach.frame.entity.DocumentElement;

class DesignWidgetPanel extends DesignWidget<PxPanel> {
	@Override
	String getTag() {
		return "PxPanel";
	}

	@Override
	PxPanel createComponent(WebForm form, DocumentElement element) {
		PxPanel panel = new PxPanel(form);
		this.init(panel, element);
		return panel;
	}

	@Override
	boolean hasChild() {
		return true;
	}

	void init(PxPanel comp, DocumentElement element) {
		super.init(comp, element);
		this.setIntProperty(comp, element, Constant.PRO_WIDTH, false, 0);
		this.setIntProperty(comp, element, Constant.PRO_HEIGHT, false, 0);
		this.setStrProperty(comp, element, Constant.PRO_TITLE, "");
		this.setBoolProperty(comp, element, Constant.PRO_COLLAPSED, false);
		this.setBoolProperty(comp, element, Constant.PRO_FIT, false);
		this.setBoolProperty(comp, element, Constant.PRO_BORDER, true);
		this.setBoolProperty(comp, element, Constant.PRO_NOHEADER, true);
		this.setStrProperty(comp, element, Constant.PRO_CLS, StringUtils.EMPTY);
	}
}
