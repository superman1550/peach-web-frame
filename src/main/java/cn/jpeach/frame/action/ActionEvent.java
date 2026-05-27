package cn.jpeach.frame.action;

public class ActionEvent {
	private final String[] parameters;

	public ActionEvent(String[] parameters) {
		super();
		this.parameters = parameters;
	}

	public String[] getParameters() {
		return parameters;
	}
}
