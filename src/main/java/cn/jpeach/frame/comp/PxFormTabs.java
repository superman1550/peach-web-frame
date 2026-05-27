package cn.jpeach.frame.comp;

import cn.jpeach.frame.core.WebContainer;
import cn.jpeach.frame.core.WebForm;

public class PxFormTabs extends WebContainer {
	public PxFormTabs(WebForm form) {
		super(form);
	}

	public void add(String title, WebForm form, boolean closeable) {
		this.addForm(form, new Object[] { title, closeable });
	}

	@Override
	protected void setAppProperty(String key, Object[] values) {

	}
}
