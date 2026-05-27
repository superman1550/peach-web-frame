package cn.jpeach.frame.entity;

public class SynchroEventData {
	private String winId;
	private String compId;
	private String event;
	private String[] parameters;

	public String getCompId() {
		return compId;
	}

	public void setCompId(String compId) {
		this.compId = compId;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String[] getParameters() {
		return parameters;
	}

	public void setParameters(String[] parameters) {
		this.parameters = parameters;
	}

	public String getWinId() {
		return this.winId;
	}

	public void setWinId(String winId) {
		this.winId = winId;
	}
}
