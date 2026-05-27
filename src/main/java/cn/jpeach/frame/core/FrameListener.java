package cn.jpeach.frame.core;

import cn.jpeach.frame.action.ActionEvent;

public abstract class FrameListener {
	protected static final String NAME_ACTION = "action";
	protected static final String NAME_SELECT = "select";
	protected static final String NAME_CHANGE = "change";
	String name;

	public FrameListener(String name) {
		this.name = name;
	}

	public abstract void actionPerformed(ActionEvent arg0);
}
