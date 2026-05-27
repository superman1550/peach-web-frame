package cn.jpeach.frame.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * 设计组件信息
 */
public class DocumentElement {
	private Map<String, String> attrs;
	private List<DocumentElement> children;
	private String name;
	private String text;

	public DocumentElement(String name, String text) {
		this.name = name;
		this.text = text;
		this.attrs = new HashMap<String, String>();
		this.children = new ArrayList<DocumentElement>();
	}

	public boolean hasAttribute(String key) {
		return this.attrs.containsKey(key);
	}

	public void putAttribute(String key, String value) {
		this.attrs.put(key, value);
	}

	public String getAttribute(String key) {
		return this.attrs.get(key);
	}

	public boolean getAttributeAsBool(String key, boolean def) {
		if (this.attrs.containsKey(key)) {
			return BooleanUtils.toBoolean(this.attrs.get(key));
		} else {
			return def;
		}
	}

	public String getAttributeAsString(String key, String def) {
		if (this.attrs.containsKey(key)) {
			return this.attrs.get(key);
		} else {
			return def;
		}
	}

	public int getAttributeAsInt(String key, int def) {
		if (this.hasAttribute(key)) {
			return NumberUtils.toInt(this.getAttribute(key));
		} else {
			return def;
		}
	}

	public void addChild(DocumentElement element) {
		this.children.add(element);
	}

	public int childrenCount() {
		return this.children.size();
	}

	public DocumentElement getChild(int index) {
		return this.children.get(index);
	}

	public String getText() {
		return this.text;
	}

	public String getName() {
		return name;
	}

}
