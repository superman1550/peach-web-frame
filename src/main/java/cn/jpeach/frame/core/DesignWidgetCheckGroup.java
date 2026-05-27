package cn.jpeach.frame.core;

import cn.jpeach.frame.comp.PxCheckGroup;
import cn.jpeach.frame.entity.DocumentElement;

class DesignWidgetCheckGroup extends DesignWidgetFormComp<PxCheckGroup> {
	@Override
	String getTag() {
		return "PxCheckGroup";
	}

	@Override
	PxCheckGroup createComponent(WebForm form, DocumentElement element) {
		PxCheckGroup group = new PxCheckGroup(form);
		this.init(group, element);
		return group;
	}

	void init(PxCheckGroup comp, DocumentElement element) {
		super.init(comp, element);
		this.setStrProperty(comp, element, Constant.PRO_DIR, "h");
		this.setIntProperty(comp, element, Constant.PRO_LABEL_WIDTH, 60);
		this.setStrProperty(comp, element, Constant.PRO_LABELS, "");
		this.setStrProperty(comp, element, Constant.PRO_VALUES, "");
	}
}
