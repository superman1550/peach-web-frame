package cn.jpeach.frame.comp;

import cn.jpeach.frame.action.ActionListener;
import cn.jpeach.frame.core.Constant;
import cn.jpeach.frame.core.WebForm;
import cn.jpeach.frame.core.WebWidget;

public final class PxLinkButton extends WebWidget {
	public PxLinkButton(WebForm form) {
		super(form);
	}

	public void setSelected(boolean selected) {
		this.setAppBoolean(Constant.PRO_SELECTED, selected);
		this.addChange(Constant.ACTION_SET_SELECTED, selected);
	}

	public void setEnable(boolean enable) {
		this.setAppBoolean(Constant.PRO_DISABLED, !enable);
		this.addChange(Constant.ACTION_SET_ENABLE, enable);
	}

	public void addActionListener(ActionListener listener) {
		this.addListener(listener);
	}

	@Override
	protected void setAppProperty(String key, Object[] values) {

	}

}
