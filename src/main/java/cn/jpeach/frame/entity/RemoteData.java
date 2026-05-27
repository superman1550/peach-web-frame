package cn.jpeach.frame.entity;

public class RemoteData {

	public static RemoteData success() {
		RemoteData data = new RemoteData();
		data.setCode(1);
		return data;
	}

	public static RemoteData error(String msg) {
		RemoteData data = new RemoteData();
		data.setCode(0);
		data.setMessage(msg);
		data.setData(new GridData());
		return data;
	}

	private int code;
	private String message;
	private GridData data;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public GridData getData() {
		return data;
	}

	public void setData(GridData data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
