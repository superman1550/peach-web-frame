package cn.jpeach.frame.core;

import cn.jpeach.frame.entity.DocumentElement;

abstract class DesignWidget<T extends WebWidget> extends DesignProperty {
	static final int LABEL_WIDTH_DEFAULT = 80;

	/**
	 * 返回组件标识
	 * 
	 * @return
	 */
	String getTag() {
		return "";
	}

	/**
	 * 是否含有子组件
	 * 
	 * @return
	 */
	boolean hasChild() {
		return false;
	}

	/**
	 * 创建组件实例
	 * 
	 * @param element
	 * @return
	 */
	abstract T createComponent(WebForm form, DocumentElement element);

	/**
	 * 初始化组件属性
	 * 
	 * @param comp
	 * @param element
	 */
	void init(T comp, DocumentElement element) {
		this.setStrProperty(comp, element, Constant.PRO_ID);
	}

}
