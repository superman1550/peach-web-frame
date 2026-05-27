package cn.jpeach.frame.core;

import org.apache.commons.lang3.StringUtils;

import cn.jpeach.frame.comp.PxComboBox;
import cn.jpeach.frame.comp.PxTextBox;
import cn.jpeach.frame.entity.DocumentElement;

class DesignWidgetComboBox extends DesignWidgetFormComp<PxComboBox> {
	@Override
	String getTag() {
		return "PxComboBox";
	}

	@Override
	PxComboBox createComponent(WebForm form, DocumentElement element) {
		PxComboBox panel = new PxComboBox(form);
		this.init(panel, element);
		return panel;
	}

	@Override
	boolean hasChild() {
		return false;
	}

	void init(PxComboBox comp, DocumentElement element) {
		super.init(comp, element);
		this.setBoolProperty(comp, element, Constant.PRO_MULTIPLE, false);
		this.setStrProperty(comp, element, Constant.PRO_SEPARATOR, ",");
		this.setBoolProperty(comp, element, Constant.PRO_REVERSED, true);
		this.setStrProperty(comp, element, Constant.PRO_LABEL, StringUtils.EMPTY);
		this.setIntProperty(comp, element, Constant.PRO_LABEL_WIDTH, LABEL_WIDTH_DEFAULT);
		this.setStrProperty(comp, element, Constant.PRO_LABEL_ALIGN, PxTextBox.ALIGN_RIGHT);
	}
}
