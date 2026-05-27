package cn.jpeach.frame.core;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 组件序列化结构实体
 */
public class SerializeComponentPeer implements Serializable {
	private static final long serialVersionUID = 3109407050320194413L;
	private String id;
	private String name;
	private Map<String, Object> properties;
	private List<String> listeners;
	private List<SerializeComponentPeer> children;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Map<String, Object> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, Object> properties) {
		this.properties = properties;
	}

	public List<SerializeComponentPeer> getChildren() {
		return children;
	}

	public void setChildren(List<SerializeComponentPeer> children) {
		this.children = children;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getListeners() {
		return listeners;
	}

	public void setListeners(List<String> listeners) {
		this.listeners = listeners;
	}

}
