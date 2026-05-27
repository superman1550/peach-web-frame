package cn.jpeach.frame.core;

import cn.jpeach.frame.comp.PxTab;
import cn.jpeach.frame.entity.DocumentElement;

class DesignWidgetTab extends DesignWidget<PxTab> {
	@Override
	String getTag() {
		return "PxTab";
	}

	@Override
	PxTab createComponent(WebForm form, DocumentElement element) {
		PxTab panel = new PxTab(form);
		this.init(panel, element);
		return panel;
	}

	@Override
	boolean hasChild() {
		return true;
	}

	void init(PxTab comp, DocumentElement element) {
		super.init(comp, element);
		this.setIntProperty(comp, element, Constant.PRO_WIDTH, 0);
		this.setIntProperty(comp, element, Constant.PRO_HEIGHT, 0);
		// 设置为true时，将不显示控制面板背景。
		this.setBoolProperty(comp, element, Constant.PRO_PLAIN, false);
		// 设置为true时，选项卡的大小将铺满它所在的容器。
		this.setBoolProperty(comp, element, Constant.PRO_FIT, false);
		// 设置为true时，显示选项卡容器边框。
		this.setBoolProperty(comp, element, Constant.PRO_BORDER, true);
		// 初始化选中一个标签页。
		this.setIntProperty(comp, element, Constant.PRO_SELECTED, 0);
		// 选项卡滚动条每次滚动的像素值。
		this.setIntProperty(comp, element, Constant.PRO_SCROLL_INCREMENT, 100);
		// 每次滚动动画持续的时间，单位：毫秒。
		this.setIntProperty(comp, element, Constant.PRO_SCROLL_DURATION, 400);
		// 工具栏位置。可用值：'left','right'。
		this.setStrProperty(comp, element, Constant.PRO_TOOL_POSITION, "right");
		// 选项卡位置。可用值：'top','bottom','left','right'。
		this.setStrProperty(comp, element, Constant.PRO_TAB_POSITION, "top");
		// 选项卡标题宽度，在tabPosition属性设置为'left'或'right'的时候才有效。
		this.setIntProperty(comp, element, Constant.PRO_HEADER_WIDTH, 150);
		// 标签条的宽度。 auto
		// this.setIntProperty(comp, element, FrameConstant.PRO_TAB_WIDTH, 150);
		// 标签条的高度。
		// this.setIntProperty(comp, element, FrameConstant.PRO_TAB_HEIGHT, 27);
		// 设置为true时，显示标签页标题。
		// this.setBoolProperty(comp, element, FrameConstant.PRO_SHOW_HEADER, true);
		// 设置为true时，生成等宽标题选项卡。
		this.setBoolProperty(comp, element, Constant.PRO_JUSTIFIED, false);
		// 设置为true时，删除选项卡标题之间的空间。
		this.setBoolProperty(comp, element, Constant.PRO_NARROW, false);
		// 设置为true时，选项卡标题样式改为气泡状。
		this.setBoolProperty(comp, element, Constant.PRO_PILL, false);
	}
}
