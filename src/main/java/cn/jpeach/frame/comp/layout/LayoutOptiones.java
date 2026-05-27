package cn.jpeach.frame.comp.layout;

public class LayoutOptiones {
	public static LayoutOptiones getDefaultOption() {
		return new LayoutOptiones(true, false, false, false);
	}

	public static LayoutOptiones getSplitOption() {
		return new LayoutOptiones(true, true, true, false);
	}

	public static LayoutOptiones getCollapsOption() {
		return new LayoutOptiones(true, true, false, true);
	}

	private boolean border;
	private boolean header;
	private boolean split;
	private boolean collapsible;

	/**
	 * layout布局选项设置
	 * 
	 * @param border      显示布局面板边框。
	 * @param header      创建面板标题。
	 * @param split       可以通过分割栏改变面板大小。
	 * @param collapsible 定义是否显示折叠按钮。
	 */
	public LayoutOptiones(boolean border, boolean header, boolean split, boolean collapsible) {
		super();
		this.border = border;
		this.header = header;
		this.split = split;
		this.collapsible = collapsible;
	}

	public boolean isSplit() {
		return split;
	}

	public void setSplit(boolean split) {
		this.split = split;
	}

	public boolean isBorder() {
		return border;
	}

	public void setBorder(boolean border) {
		this.border = border;
	}

	public boolean isHeader() {
		return header;
	}

	public void setHeader(boolean header) {
		this.header = header;
	}

	public boolean isCollapsible() {
		return collapsible;
	}

	public void setCollapsible(boolean collapsible) {
		this.collapsible = collapsible;
	}

}
