package cn.jpeach.frame.core;

import cn.jpeach.frame.comp.PxRadioGroup;
import cn.jpeach.frame.entity.DocumentElement;

class DesignWidgetRadioGroup extends DesignWidget<PxRadioGroup> {
	@Override
	String getTag() {
		return "PxRadioGroup";
	}

	@Override
	PxRadioGroup createComponent(WebForm form, DocumentElement element) {
		PxRadioGroup group = new PxRadioGroup(form);
		this.init(group, element);
		return group;
	}

	@Override
	void init(PxRadioGroup comp, DocumentElement element) {
		super.init(comp, element);
		this.setStrProperty(comp, element, Constant.PRO_DIR, "h");
		this.setIntProperty(comp, element, Constant.PRO_LABEL_WIDTH, 60);
		this.setStrProperty(comp, element, Constant.PRO_LABELS, "");
		this.setStrProperty(comp, element, Constant.PRO_VALUES, "");
	}
}
