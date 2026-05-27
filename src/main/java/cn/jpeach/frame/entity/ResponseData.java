package cn.jpeach.frame.entity;

public class ResponseData {
	private long timestamp;
	private int code;
	private String message;
	private Object data;

	public static ResponseData success() {
		ResponseData info = new ResponseData();
		info.setCode(1);
		info.setMessage("OK");
		info.setTimestamp(System.currentTimeMillis());
		return info;
	}

	public static ResponseData error(String msg) {
		ResponseData info = new ResponseData();
		info.setCode(0);
		info.setMessage(msg);
		info.setTimestamp(System.currentTimeMillis());
		return info;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
