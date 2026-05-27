package cn.jpeach.frame.core;

import cn.jpeach.frame.comp.PxAccordionPanel;
import cn.jpeach.frame.entity.DocumentElement;

class DesignWidgetAccordionPanel extends DesignWidget<PxAccordionPanel> {
	@Override
	protected String getTag() {
		return "PxAccordionPanel";
	}

	@Override
	protected PxAccordionPanel createComponent(WebForm form, DocumentElement element) {
		PxAccordionPanel accordion = new PxAccordionPanel(form);
		this.init(accordion, element);
		return accordion;
	}

	@Override
	protected boolean hasChild() {
		return true;
	}

	protected void init(PxAccordionPanel comp, DocumentElement element) {
		super.init(comp, element);
		this.setStrProperty(comp, element, Constant.PRO_TITLE, "");
		this.setBoolProperty(comp, element, Constant.PRO_BORDER, true);
	}
}
