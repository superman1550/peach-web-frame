package cn.jpeach.frame.core;

import cn.jpeach.frame.dao.DataCreate;
import cn.jpeach.frame.dao.DataDao;

public abstract class WebWidget extends WebComponent {
	final WebForm form;

	public WebWidget(WebForm form) {
		super();
		this.form = form;
	}

	protected void addChange(String action, Object data) {
		this.addChange(this.form.appId, this.appId, action, data);
	}

	protected void addListener(FrameListener listener) {
		this.addAppListener(listener);
	}

	protected DataDao getDao(String id) {
		return DataCreate.getDataDao(form, id);
	}
}
