package cn.jpeach.frame.core;

import java.util.ArrayList;
import java.util.List;

import cn.jpeach.frame.entity.DocumentElement;
import cn.jpeach.frame.exception.FrameException;

class DesignFormComponent {

	private static List<DesignWidget<?>> compCache = new ArrayList<DesignWidget<?>>();

	static {
		compCache.add(new DesignWidgetAccordion());
		compCache.add(new DesignWidgetAccordionPanel());
		compCache.add(new DesignWidgetCalendar());
		compCache.add(new DesignWidgetCheckGroup());
		compCache.add(new DesignWidgetComboBox());
		compCache.add(new DesignWidgetComboGrid());
		compCache.add(new DesignWidgetDataGrid());
		compCache.add(new DesignWidgetDateBox());
		compCache.add(new DesignWidgetLayout());
		compCache.add(new DesignWidgetLinkButton());
		compCache.add(new DesignWidgetMenu());
		compCache.add(new DesignWidgetNumberSpinner());
		compCache.add(new DesignWidgetPanel());
		compCache.add(new DesignWidgetSideMenu());
		compCache.add(new DesignWidgetSwitchButton());
		compCache.add(new DesignWidgetTab());
		compCache.add(new DesignWidgetTextBox());
		compCache.add(new DesignWidgetTree());

		compCache.add(new DesignWidgetFormAccordion());
		compCache.add(new DesignWidgetFormTabs());
	}

	static void create(WebForm form, WebComponent parent, DocumentElement element) {
		DesignWidget<?> reflection = find(element.getName());
		WebWidget widget = reflection.createComponent(form, element);
		if (reflection.hasChild()) {
			for (int i = 0; i < element.childrenCount(); i++) {
				create(form, widget, element.getChild(i));
			}
		}
		parent.children.add(widget);
	}

	static DesignWidget<?> find(String name) {
		for (DesignWidget<?> reflection : compCache) {
			if (name.equals(reflection.getTag())) {
				return reflection;
			}
		}
		throw new FrameException(String.format("未找到[%s]組件注册信息", name));
	}
}
