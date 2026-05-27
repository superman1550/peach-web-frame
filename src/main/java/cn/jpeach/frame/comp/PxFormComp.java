package cn.jpeach.frame.comp;

import cn.jpeach.frame.core.Constant;
import cn.jpeach.frame.core.WebForm;
import cn.jpeach.frame.core.WebWidget;

public abstract class PxFormComp extends WebWidget {

	public PxFormComp(WebForm form) {
		super(form);
	}

	public Boolean isReadonly() {
		return this.getAppBoolean(Constant.PRO_READONLY);
	}


	public boolean isEnable() {
		return !this.getAppBoolean(Constant.PRO_DISABLED);
	}

	public void setReadonly(boolean value) {
		this.setAppBoolean(Constant.PRO_READONLY, value);
		this.addChange(Constant.ACTION_SET_READONLY, value);
	}

	public void setEnable(boolean enable) {
		this.setAppBoolean(Constant.PRO_DISABLED, !enable);
		this.addChange(Constant.ACTION_SET_ENABLE, enable);
	}
}
