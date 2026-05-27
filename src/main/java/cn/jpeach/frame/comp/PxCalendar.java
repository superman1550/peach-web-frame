package cn.jpeach.frame.comp;

import java.util.Date;

import cn.jpeach.frame.action.SelectListener;
import cn.jpeach.frame.core.Constant;
import cn.jpeach.frame.core.WebForm;
import cn.jpeach.frame.core.WebWidget;

public final class PxCalendar extends WebWidget {
	public PxCalendar(WebForm form) {
		super(form);
	}

	public void setDate(Date value) {
		long lv = value.getTime();
		this.setAppLong(Constant.PRO_VALUE, lv);
		this.addChange(Constant.ACTION_SET_VALUE, lv);
	}

	public Date getDate() {
		return new Date(this.getAppLong(Constant.PRO_VALUE));
	}

	public void addSelectListener(SelectListener listener) {
		this.addListener(listener);
	}

	@Override
	protected void setAppProperty(String key, Object[] values) {
		if (Constant.PRO_VALUE.equals(key)) {
			this.setAppLong(Constant.PRO_VALUE, ((Number) values[0]).longValue());
		}
	}

}
