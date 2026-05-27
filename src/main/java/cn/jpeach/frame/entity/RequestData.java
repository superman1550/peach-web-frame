package cn.jpeach.frame.entity;

public class RequestData {
	private long timestamp;
	private String sessionid;
	private SynchroData sync;

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public SynchroData getSync() {
		return sync;
	}

	public void setSync(SynchroData sync) {
		this.sync = sync;
	}

	public String getSessionid() {
		return sessionid;
	}

	public void setSessionid(String sessionid) {
		this.sessionid = sessionid;
	}

}
