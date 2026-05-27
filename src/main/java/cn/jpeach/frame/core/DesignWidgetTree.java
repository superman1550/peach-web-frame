package cn.jpeach.frame.core;

import java.util.ArrayList;

import cn.jpeach.frame.comp.PxTree;
import cn.jpeach.frame.entity.DocumentElement;

class DesignWidgetTree extends DesignWidget<PxTree> {
	@Override
	String getTag() {
		return "PxTree";
	}

	@Override
	PxTree createComponent(WebForm form, DocumentElement element) {
		PxTree panel = new PxTree(form);
		this.init(panel, element);
		return panel;
	}

	@Override
	boolean hasChild() {
		return false;
	}

	void init(PxTree comp, DocumentElement element) {
		super.init(comp, element);
		this.setBoolProperty(comp, element, Constant.PRO_ANIMATE, false);
		// 定义是否在每一个借点之前都显示复选框。
		this.setBoolProperty(comp, element, Constant.PRO_CHECKBOX, false);
		// 定义是否层叠选中状态。
		this.setBoolProperty(comp, element, Constant.PRO_CASCADE_CHECK, true);
		// 定义是否只在末级节点之前显示复选框。
		this.setBoolProperty(comp, element, Constant.PRO_ONLY_LEAF_CHECK, false);
		// 定义是否显示树控件上的虚线。
		this.setBoolProperty(comp, element, Constant.PRO_LINES, false);
		// 节点数据加载。
		this.setObjectProperty(comp, Constant.PRO_DATA, new ArrayList<>());
	}
}
