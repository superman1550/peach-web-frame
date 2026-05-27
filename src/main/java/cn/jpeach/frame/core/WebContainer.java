package cn.jpeach.frame.core;

import cn.jpeach.frame.util.ArrayUtil;

public abstract class WebContainer extends WebWidget {
	public WebContainer(WebForm form) {
		super(form);
	}

	protected void addForm(WebForm form, Object[] options) {
		FrameInstance instance = FrameInstance.getContext();
		Object[] opt = new Object[] { SerializeComponent.create(form) };
		this.addChange(Constant.ACTION_ADD_FORM, ArrayUtil.merge(opt, options));
		instance.addSection(form);
	}
}
