package cn.jpeach.frame.comp;

import java.util.Objects;

import cn.jpeach.frame.action.ChangeListener;
import cn.jpeach.frame.core.Constant;
import cn.jpeach.frame.core.WebForm;

public final class PxTextBox extends PxFormComp {
	public static final String ALIGN_LEFT = "left";
	public static final String ALIGN_CENTER = "center";
	public static final String ALIGN_RIGHT = "right";

	public PxTextBox(WebForm form) {
		super(form);
	}

	public String getText() {
		return this.getAppString(Constant.PRO_VALUE);
	}

	public void setText(String text) {
		this.setAppString(Constant.PRO_VALUE, text);
		this.addChange(Constant.ACTION_SET_TEXT, text);
	}

	public void addChangeListener(ChangeListener listener) {
		this.addListener(listener);
	}

	@Override
	protected void setAppProperty(String key, Object[] values) {
		if (Constant.PRO_VALUE.equals(key)) {
			this.setAppString(Constant.PRO_VALUE, Objects.toString(values[0], null));
		}
	}
}
