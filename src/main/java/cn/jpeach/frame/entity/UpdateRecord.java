package cn.jpeach.frame.entity;

public class UpdateRecord {
	private String winId;
	private String compId;
	private String action;
	private Object data;

	public UpdateRecord(String winId, String compId, String action, Object data) {
		super();
		this.winId = winId;
		this.compId = compId;
		this.action = action;
		this.data = data;
	}

	public String getWinId() {
		return winId;
	}

	public void setWinId(String winId) {
		this.winId = winId;
	}

	public String getCompId() {
		return compId;
	}

	public void setCompId(String compId) {
		this.compId = compId;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
