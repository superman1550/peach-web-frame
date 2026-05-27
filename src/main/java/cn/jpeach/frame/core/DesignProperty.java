package cn.jpeach.frame.core;

import org.apache.commons.lang3.math.NumberUtils;

import cn.jpeach.frame.entity.DocumentElement;

class DesignProperty {
	/**
	 * 
	 * @param component 组件
	 * @param element   原型元素
	 * @param pro       属性名称
	 */
	void setIntProperty(WebComponent component, DocumentElement element, String pro) {
		this.setIntProperty(component, element, pro, false, 0);
	}

	void setIntProperty(WebComponent component, DocumentElement element, String pro, int def) {
		this.setIntProperty(component, element, pro, true, def);
	}

	/**
	 * 
	 * @param component 组件
	 * @param element   原型元素
	 * @param pro       属性名称
	 * @param required  是否必填
	 * @param def       必填属性值为空时的默认值
	 */
	void setIntProperty(WebComponent component, DocumentElement element, String pro, boolean required, int def) {
		if (element.hasAttribute(pro)) {
			component.setAppInt(pro, NumberUtils.toInt(element.getAttribute(pro)));
		} else if (required) {
			component.setAppInt(pro, def);
		}
	}

	/**
	 * 
	 * @param component 组件
	 * @param element   原型元素
	 * @param pro       属性名称
	 */
	void setStrProperty(WebComponent component, DocumentElement element, String pro) {
		this.setStrProperty(component, element, pro, false, "");
	}

	void setStrProperty(WebComponent component, DocumentElement element, String pro, String def) {
		this.setStrProperty(component, element, pro, true, def);
	}

	/**
	 * 
	 * @param component 组件
	 * @param element   原型元素
	 * @param pro       属性名称
	 * @param required  是否必填
	 * @param def       必填属性值为空时的默认值
	 */
	void setStrProperty(WebComponent component, DocumentElement element, String pro, boolean required, String def) {
		component.setAppString(pro, element.getAttributeAsString(pro, def));
	}

	/**
	 * 
	 * @param component 组件
	 * @param element   原型元素
	 * @param pro       属性名称
	 */
	void setBoolProperty(WebComponent component, DocumentElement element, String pro) {
		this.setBoolProperty(component, element, pro, false, false);
	}

	void setBoolProperty(WebComponent component, DocumentElement element, String pro, boolean def) {
		this.setBoolProperty(component, element, pro, true, def);
	}

	/**
	 * 
	 * @param component 组件
	 * @param element   原型元素
	 * @param pro       属性名称
	 * @param required  是否必填
	 * @param def       必填属性值为空时的默认值
	 */
	void setBoolProperty(WebComponent component, DocumentElement element, String pro, boolean required, boolean def) {
		component.setAppBoolean(pro, element.getAttributeAsBool(pro, def));
	}

	void setObjectProperty(WebComponent component, String pro, Object value) {
		component.setAppObject(pro, value);
	}
}
