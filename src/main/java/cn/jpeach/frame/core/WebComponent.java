package cn.jpeach.frame.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import cn.jpeach.frame.action.ActionEvent;
import cn.jpeach.frame.entity.DataParameter;
import cn.jpeach.frame.entity.GridData;

public abstract class WebComponent extends FrameObject {
	/**
	 * 将前端属性同步修改本地
	 * 
	 * @param key
	 * @param values
	 */
	protected abstract void setAppProperty(String key, Object[] values);

	/**
	 * 返回前端获取的数据
	 * 
	 * @param parameter
	 * @return
	 */
	protected GridData remoteData(DataParameter parameter) {
		return new GridData();
	}

	final String appId = "pui-"+UUID.randomUUID().toString().replace("-", "");
	/**
	 * 組件的基础属性
	 */
	final Map<String, Object> properties = new ConcurrentHashMap<String, Object>();
	/**
	 * 子組件列表
	 */
	final List<WebComponent> children = new ArrayList<WebComponent>();
	/**
	 * 事件列表
	 */
	final List<FrameListener> listeners = new ArrayList<FrameListener>();

	/******************* 属性设置相关 开始********************************************** */

	protected void setAppInt(String key, int value) {
		properties.put(key, value);
	}

	protected int getAppInt(String key) {
		Object value = properties.get(key);
		if (value instanceof Number) {
			return ((Number) value).intValue();
		}
		return 0;
	}

	protected void setAppLong(String key, long value) {
		properties.put(key, value);
	}

	protected Long getAppLong(String key) {
		Object value = properties.get(key);
		if (value instanceof Number) {
			return ((Number) value).longValue();
		}
		return 0L;
	}

	protected void setAppFloat(String key, Float value) {
		properties.put(key, value);
	}

	protected Float getAppFloat(String key) {
		Object value = properties.get(key);
		if (value instanceof Number) {
			return ((Number) value).floatValue();
		}
		return 0f;
	}

	protected void setAppString(String key, String value) {
		properties.put(key, value);
	}

	protected String getAppString(String key) {
		Object value = properties.get(key);
		if (value instanceof String) {
			return value.toString();
		}
		return "";
	}

	protected void setAppBoolean(String key, Boolean value) {
		properties.put(key, value);
	}

	protected Boolean getAppBoolean(String key) {
		Object value = properties.get(key);
		if (value instanceof Boolean) {
			return (Boolean) value;
		}
		return false;
	}

	protected void setAppObject(String key, Object value) {
		properties.put(key, value);
	}

	protected Object getAppObject(String key) {
		return properties.get(key);
	}

	/******************* 属性设置相关 结束********************************************** */

	/******************* 子组件相关 开始********************************************** */
	void addChild(WebComponent component) {
		this.children.add(component);
	}

	int getChildCount() {
		return this.children.size();
	}

	WebComponent getChild(int index) {
		return this.children.get(index);
	}

	WebComponent getChild(String appid) {
		return this.getChild(this.children, appid);
	}

	private WebComponent getChild(List<WebComponent> components, String appid) {
		for (WebComponent component : components) {
			if (component.appId.equals(appid)) {
				return component;
			}
			WebComponent findComponent = this.getChild(component.children, appid);
			if (findComponent != null) {
				return findComponent;
			}
		}
		return null;
	}

	/******************* 子组件相关 结束********************************************** */

	/******************* 事件相关 开始********************************************** */
	void fireAppEvent(String event, String[] parameters) {
		for (FrameListener listener : this.listeners) {
			if (listener.name.equals(event)) {
				listener.actionPerformed(new ActionEvent(parameters));
			}
		}
	}

	void addAppListener(FrameListener listener) {
		this.listeners.add(listener);
	}

	/******************* 事件相关 结束********************************************** */
}
