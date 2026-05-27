package cn.jpeach.frame.core;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;

public class FrameLogger {
	FrameLogger(Logger logger) {
		super();
		this.logger = logger;
	}

	private Logger logger;
	private String ip;
	private String id;
	private WebForm form;
	private WebComponent component;
	private String event;

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public WebForm getForm() {
		return form;
	}

	public void setForm(WebForm form) {
		this.form = form;
	}

	public WebComponent getComponent() {
		return component;
	}

	public void setComponent(WebComponent component) {
		this.component = component;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void clear() {
		this.form = null;
		this.component = null;
		this.event = null;
	}

	public void info(String message) {
		logger.info(getPathMessage() + message);
	}

	public void debug(String message) {
		logger.debug(getPathMessage() + message);
	}

	public void error(String message, Throwable throwable) {
		logger.error(getPathMessage() + message, throwable);
	}

	private String getPathMessage() {
		StringBuffer buffer = new StringBuffer();
		if (StringUtils.isNotBlank(id)) {
			buffer.append(id + "|");
		}
		if (StringUtils.isNotBlank(ip)) {
			buffer.append(ip + "|");
		}
		if (form != null) {
			buffer.append(String.format("form=%s|", form.getClass().getName()));
			if (component != null) {
				buffer.append(String.format("component=%s|", component.getClass().getName()));
				buffer.append(String.format("%s|", component.getAppString(Constant.PRO_ID)));
			}
		}
		if (StringUtils.isNotBlank(event)) {
			buffer.append(String.format("%s|", event));
		}
		return buffer.toString();
	}
}
