package cn.jpeach.frame.comp;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import cn.jpeach.frame.action.SelectListener;
import cn.jpeach.frame.core.Constant;
import cn.jpeach.frame.core.WebForm;
import cn.jpeach.frame.dao.DaoParameter;
import cn.jpeach.frame.dao.DataDao;
import cn.jpeach.frame.dao.StaticDaoDataManager;
import cn.jpeach.frame.entity.DataParameter;
import cn.jpeach.frame.entity.GridData;

public final class PxComboGrid extends PxFormComp {
	private Object[] parameters;

	public PxComboGrid(WebForm form) {
		super(form);
	}

	public void setLoadParam(Object[] parameters) {
		this.parameters = parameters;
		this.addChange(Constant.ACTION_SET_LOAD_PARAM, new Object[] {});
	}

	public Object getValue() {
		return this.getAppObject(Constant.PRO_VALUE);
	}

	public void setValue(Object value, String text) {
		this.setAppObject(Constant.PRO_VALUE, value);
		this.setAppObject(Constant.PRO_TEXT, text);
		this.addChange(Constant.ACTION_SET_VALUE, new Object[] { value, text });
	}

	@Override
	protected GridData remoteData(DataParameter parameter) {
		if (this.parameters == null) {
			return new GridData();
		}
		String daoid = this.getAppString(Constant.PRO_DAO);
		DataDao dao = this.getDao(daoid);
		if (parameter.getPage() == 1) {
			List<Object> objects = new ArrayList<Object>();
			for (int i = 0; i < this.parameters.length; i++) {
				Object pt;
				if (this.parameters[i] == DaoParameter.KEY) {
					if (StringUtils.isEmpty(parameter.getKey())) {
						pt = "";
					} else {
						pt = parameter.getKey().trim();
					}
				} else if (this.parameters[i] == DaoParameter.LIKE_KEY) {
					if (StringUtils.isEmpty(parameter.getKey())) {
						pt = "%";
					} else {
						pt = String.format("%%%s%%", parameter.getKey().trim());
					}
				} else if (this.parameters[i] == DaoParameter.LIKE_XKEY) {
					if (StringUtils.isEmpty(parameter.getKey())) {
						pt = "%";
					} else {
						pt = String.format("%%%s", parameter.getKey().trim());
					}
				} else if (this.parameters[i] == DaoParameter.LIKE_KEYX) {
					if (StringUtils.isEmpty(parameter.getKey())) {
						pt = "%";
					} else {
						pt = String.format("%s%", parameter.getKey().trim());
					}
				} else {
					pt = this.parameters[i];
				}
				objects.add(pt);
			}
			dao.setParameters(objects.toArray(new Object[] {}));
			dao.reload();
		}
		return StaticDaoDataManager.toData(dao, parameter);
	}

	public void addSelectListener(SelectListener listener) {
		this.addListener(listener);
	}

	@Override
	protected void setAppProperty(String key, Object[] values) {
		if (Constant.PRO_VALUE.equalsIgnoreCase(key)) {
			this.setAppObject(Constant.PRO_VALUE, values[0]);
			this.setAppObject(Constant.PRO_TEXT, values[1]);
		}
	}
}
