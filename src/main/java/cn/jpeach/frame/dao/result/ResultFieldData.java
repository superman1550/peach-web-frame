package cn.jpeach.frame.dao.result;

import java.io.Serializable;

public class ResultFieldData implements Serializable {
	private static final long serialVersionUID = 2258099445519951307L;
	private String label;
	private Object oldValue;
	private int type;
	private boolean update;
	private Object newValue;

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Object getOldValue() {
		return oldValue;
	}

	public void setOldValue(Object oldValue) {
		this.oldValue = oldValue;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public boolean isUpdate() {
		return update;
	}

	public void setUpdate(boolean update) {
		this.update = update;
	}

	public Object getNewValue() {
		return newValue;
	}

	public void setNewValue(Object newValue) {
		this.newValue = newValue;
	}

}
