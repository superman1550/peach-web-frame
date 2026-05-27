package cn.jpeach.frame.comp;

import org.apache.commons.lang3.math.NumberUtils;

import cn.jpeach.frame.action.SelectListener;
import cn.jpeach.frame.core.Constant;
import cn.jpeach.frame.core.WebForm;
import cn.jpeach.frame.core.WebWidget;

public final class PxAccordion extends WebWidget {
	public PxAccordion(WebForm form) {
		super(form);
	}

	public void select(int index) {
		this.setAppInt(Constant.PRO_SELECTED, index);
		this.addChange(Constant.ACTION_SELECT, index);
	}

	protected void addSelectListener(SelectListener listener) {
		this.addListener(listener);
	}

	@Override
	protected void setAppProperty(String key, Object[] values) {
		if (Constant.PRO_SELECTED.equals(key)) {
			this.updateSelected(values[0]);
		}
	}

	private void updateSelected(Object obj) {
		this.setAppInt(Constant.PRO_SELECTED, NumberUtils.toInt(obj.toString()));
	}
}
