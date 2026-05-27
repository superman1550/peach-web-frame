package cn.jpeach.frame.core;

import org.apache.commons.lang3.StringUtils;

import cn.jpeach.frame.comp.PxTextBox;
import cn.jpeach.frame.entity.DocumentElement;

class DesignWidgetTextBox extends DesignWidgetFormComp<PxTextBox> {
	@Override
	String getTag() {
		return "PxTextBox";
	}

	@Override
	PxTextBox createComponent(WebForm form, DocumentElement element) {
		PxTextBox panel = new PxTextBox(form);
		this.init(panel, element);
		return panel;
	}

	@Override
	boolean hasChild() {
		return false;
	}

	void init(PxTextBox comp, DocumentElement element) {
		super.init(comp, element);
		this.setStrProperty(comp, element, Constant.PRO_PROMPT, "");
		this.setStrProperty(comp, element, Constant.PRO_VALUE, "");
		this.setStrProperty(comp, element, Constant.PRO_TYPE, "text");
		this.setStrProperty(comp, element, Constant.PRO_LABEL, StringUtils.EMPTY);
		this.setIntProperty(comp, element, Constant.PRO_LABEL_WIDTH, LABEL_WIDTH_DEFAULT);
		this.setStrProperty(comp, element, Constant.PRO_LABEL_ALIGN, PxTextBox.ALIGN_RIGHT);
		this.setBoolProperty(comp, element, Constant.PRO_MULTILINE, false);
	}
}
