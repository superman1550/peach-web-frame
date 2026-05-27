package cn.jpeach.frame.core;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.jpeach.frame.conf.ConfigCache;
import cn.jpeach.frame.entity.GridData;
import cn.jpeach.frame.entity.RemoteParam;
import cn.jpeach.frame.entity.SynchroData;
import cn.jpeach.frame.entity.SynchroEventData;
import cn.jpeach.frame.entity.SynchroStackData;
import cn.jpeach.frame.entity.UpdateRecord;
import cn.jpeach.frame.exception.FrameException;
import cn.jpeach.frame.util.Reflector;

public final class FrameInstance {
	private static final ThreadLocal<FrameInstance> FRAME_CONTEXT = new ThreadLocal<FrameInstance>();
	private static final Logger LOGGER = LoggerFactory.getLogger(FrameInstance.class);

	public static FrameInstance getContext() {
		return FRAME_CONTEXT.get();
	}

	static void init(FrameInstance instance) {
		FRAME_CONTEXT.set(instance);
	}

	static void clean() {
		FRAME_CONTEXT.remove();
	}

	/** 后台Form实例 */
	private Map<String, WebForm> _forms;
	/** 后台同步修改 */
	private List<UpdateRecord> _changes;
	/** 后台全局属性 */
	private Map<String, Object> _attributes;
	/** 全局日志調用 */
	private FrameLogger _logger;

	/** 窗口实例 */
	private FrameWindow window;
	/** 前端IP */
	private String clientIp;
	private String sessionId;
	private String mainhtml;

	public FrameWindow getWindow() {
		return this.window;
	}

	public String getClientIp() {
		return clientIp;
	}

	public void setAttribute(String key, Object value) {
		_attributes.put(key, value);
	}

	public Object getAttribute(String key) {
		return _attributes.get(key);
	}

	public void printInfo(String info) {
		this._logger.info(info);
	}

	public void printError(String info, Throwable throwable) {
		this._logger.error(info, throwable);
	}

	/**
	 * 设置客户端IP
	 * 
	 * @param clientIp
	 */
	void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}

	/**
	 * 设置会话ID
	 * 
	 * @param id
	 */
	void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	/**
	 * 返回系统主页
	 * 
	 * @return
	 * @throws Exception
	 */
	String start(ServletContext context) throws Exception {
		String mainClass = ConfigCache.getApplicationConifg().getMainClass();
		this._forms = new HashMap<String, WebForm>();
		this._attributes = new HashMap<String, Object>();
		this._logger = new FrameLogger(LOGGER);
		this.window = (FrameWindow) Reflector.getClassByFullName(FrameWindow.class, mainClass);
		if (StringUtils.isEmpty(mainhtml)) {
			StringBuilder sb = new StringBuilder();
			// 获取输入流（路径相对于 Web 应用根目录）
			try (InputStream in = context.getResourceAsStream("/index.html")) {
				// 转换为字符串
				try (BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"))) {
					char[] buffer = new char[1024];
					int len;
					while ((len = reader.read(buffer)) != -1) {
						sb.append(buffer, 0, len);
					}
				}
			}
			this.mainhtml = sb.toString();
		}
		return this.mainhtml;
	}

	/**
	 * 执行前后端事件同步
	 * 
	 * @param data
	 * @return
	 */
	Object[] exceute(SynchroData data) {
		this._changes = new ArrayList<UpdateRecord>();
		try {
			SynchroEventData event = data.getEvent();
			SynchroStackData[] stacks = data.getStacks();

			for (SynchroStackData stack : stacks) {
				String winId = stack.getWinId();
				String compId = stack.getCompId();
				String property = stack.getProperty();
				Object[] values = stack.getValues();
				if (this._forms.containsKey(winId)) {
					WebForm form = this._forms.get(winId);
					if (StringUtils.isEmpty(compId)) {
						form.setAppProperty(property, values);
					} else {
						WebComponent component = form.getChild(compId);
						component.setAppProperty(property, values);
					}
				}
			}

			if (this._forms.containsKey(event.getWinId())) {
				WebForm form = this._forms.get(event.getWinId());
				this._logger.setIp(clientIp);
				this._logger.setId(sessionId);
				this._logger.setForm(form);
				this._logger.setEvent(event.getEvent());
				StopWatch stopWatch = StopWatch.createStarted();
				if (StringUtils.isEmpty(event.getCompId())) {
					form.fireAppEvent(event.getEvent(), event.getParameters());
				} else {
					WebComponent component = form.getChild(event.getCompId());
					component.fireAppEvent(event.getEvent(), event.getParameters());
					this._logger.setComponent(component);
				}
				stopWatch.stop();
				this._logger.info(String.format("time=%sms", stopWatch.getTime()));
			} else {
				this.window.create(event.getParameters());
			}
			return this._changes.toArray();
		} finally {
			this._logger.clear();
			this._changes.clear();
		}
	}

	/**
	 * 前端数据控件获取后台数据列表
	 * 
	 * @param info
	 * @return
	 */
	GridData remote(RemoteParam info) {
		try {
			String winId = info.getWinId();
			String compId = info.getCompId();
			if (this._forms.containsKey(winId)) {
				WebForm form = this._forms.get(winId);
				WebComponent component = form.getChild(compId);
				this._logger.setIp(clientIp);
				this._logger.setId(sessionId);
				this._logger.setForm(form);
				this._logger.setComponent(component);
				this._logger.setEvent("remote");
				return component.remoteData(info.getParameter());
			} else {
				throw new FrameException(String.format("未找到窗体【%s】", winId));
			}
		} finally {
			this._logger.clear();
		}
	}

	void addSection(WebForm form) {
		this._forms.put(form.appId, form);
	}

	void removeSection(WebForm form) {
		this._forms.remove(form.appId);
	}

	void addChange(UpdateRecord changeProperty) {
		this._changes.add(changeProperty);
	}

}
