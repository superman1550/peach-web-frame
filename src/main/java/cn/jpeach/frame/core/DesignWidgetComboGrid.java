package cn.jpeach.frame.core;

import org.apache.commons.lang3.StringUtils;

import cn.jpeach.frame.comp.PxComboGrid;
import cn.jpeach.frame.comp.PxTextBox;
import cn.jpeach.frame.entity.DocumentElement;

class DesignWidgetComboGrid extends DesignWidgetFormComp<PxComboGrid> {
	@Override
	String getTag() {
		return "PxComboGrid";
	}

	@Override
	PxComboGrid createComponent(WebForm form, DocumentElement element) {
		PxComboGrid panel = new PxComboGrid(form);
		this.init(panel, element);
		return panel;
	}

	@Override
	boolean hasChild() {
		return false;
	}

	void init(PxComboGrid comp, DocumentElement element) {
		super.init(comp, element);
		this.setStrProperty(comp, element, Constant.PRO_ID_FIELD, "");
		this.setStrProperty(comp, element, Constant.PRO_TEXT_FIELD, "");
		this.setBoolProperty(comp, element, Constant.PRO_FIT_COLUMNS, true);
		this.setBoolProperty(comp, element, Constant.PRO_STRIPED, true);
		this.setStrProperty(comp, element, Constant.PRO_LABELS, "");
		this.setStrProperty(comp, element, Constant.PRO_VALUES, "");
		this.setIntProperty(comp, element, Constant.PRO_PANEL_WIDTH, 530);
		this.setStrProperty(comp, element, Constant.PRO_DAO);
		this.setStrProperty(comp, element, Constant.PRO_LABEL, StringUtils.EMPTY);
		this.setIntProperty(comp, element, Constant.PRO_LABEL_WIDTH, LABEL_WIDTH_DEFAULT);
		this.setStrProperty(comp, element, Constant.PRO_LABEL_ALIGN, PxTextBox.ALIGN_RIGHT);
	}
}
