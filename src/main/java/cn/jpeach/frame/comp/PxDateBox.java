package cn.jpeach.frame.comp;

import java.util.Date;

import org.apache.commons.lang3.math.NumberUtils;

import cn.jpeach.frame.core.Constant;
import cn.jpeach.frame.core.WebForm;

public final class PxDateBox extends PxFormComp {
	public PxDateBox(WebForm form) {
		super(form);
	}

	public Date getDate() {
		return new Date(this.getAppLong(Constant.PRO_VALUE));
	}

	public void setDate(Date date) {
		this.setAppObject(Constant.PRO_VALUE, date.getTime());
		this.addChange(Constant.ACTION_SET_VALUE, date.getTime());
	}

	@Override
	protected void setAppProperty(String pro, Object[] values) {
		if (Constant.PRO_VALUE.equals(pro)) {
			if (values[0] != null) {
				this.setAppObject(Constant.PRO_VALUE, NumberUtils.toLong(values[0].toString(), 0));
			}
		}
	}

}
