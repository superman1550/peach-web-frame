package cn.jpeach.frame.comp;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import cn.jpeach.frame.action.ChangeListener;
import cn.jpeach.frame.action.SelectListener;
import cn.jpeach.frame.comp.combo.PxItem;
import cn.jpeach.frame.core.Constant;
import cn.jpeach.frame.core.WebForm;
import cn.jpeach.frame.util.ArrayUtil;

public final class PxComboBox extends PxFormComp {
	private List<PxItem> items;
	private String[] values;

	public PxComboBox(WebForm form) {
		super(form);
		this.items = new ArrayList<PxItem>();
	}

	public Boolean isMultiple() {
		return this.getAppBoolean(Constant.PRO_MULTIPLE);
	}

	public String getSeparator() {
		return this.getAppString(Constant.PRO_SEPARATOR);
	}

	public String getValue() {
		if (ArrayUtils.isEmpty(this.values)) {
			return null;
		} else {
			return this.values[0];
		}
	}

	public String[] getValues() {
		return this.values;
	}

	public void setData(List<PxItem> items) {
		this.items = items;
		this.setAppObject(Constant.PRO_DATA, items);
		this.addChange(Constant.ACTION_SET_DATA, items);
	}

	public void setValue(String value) {
		if (ArrayUtils.isNotEmpty(this.values) && this.values.length == 1 && this.values[0].equals(value)) {
			return;
		}
		if (this.findValue(value)) {
			this.values = new String[] { value };
			this.setAppObject(Constant.PRO_VALUE, this.values);
			this.addChange(Constant.ACTION_SET_VALUE, this.values);
		}
	}

	public void setValues(String[] values) {
		List<String> vlist = new ArrayList<String>();
		for (String value : values) {
			if (this.findValue(value)) {
				vlist.add(value);
			}
		}
		if (vlist.size() > 0) {
			this.values = vlist.toArray(new String[] {});
			this.setAppObject(Constant.PRO_VALUE, this.values);
			this.addChange(Constant.ACTION_SET_VALUE, this.values);
		}
	}

	public void addSelectListener(SelectListener listener) {
		this.addListener(listener);
	}
	
	public void addChangeListener(ChangeListener listener) {
		this.addListener(listener);
	}

	@Override
	protected void setAppProperty(String pro, Object[] values) {
		if (Constant.PRO_VALUE.equals(pro)) {
			this.values = ArrayUtil.toString(values);
			this.setAppObject(Constant.PRO_VALUE, this.values);
		}
	}

	private boolean findValue(String value) {
		for (PxItem item : this.items) {
			if (StringUtils.isNotBlank(item.getValue()) && item.getValue().equals(value)) {
				return true;
			}
		}
		return false;
	}
}
