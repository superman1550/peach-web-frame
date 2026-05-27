package cn.jpeach.frame.entity;

public class SynchroStackData {
	private String winId;
	private String compId;
	private String property;
	private Object[] values;

	public String getCompId() {
		return compId;
	}

	public void setCompId(String compId) {
		this.compId = compId;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public Object[] getValues() {
		return values;
	}

	public void setValues(Object[] values) {
		this.values = values;
	}

	public String getWinId() {
		return this.winId;
	}

	public void setWinId(String winId) {
		this.winId = winId;
	}
}
