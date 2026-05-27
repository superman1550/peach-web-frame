package cn.jpeach.frame.comp.datagrid;

public abstract class DxDataGridAbstractColumn {
	public static final String COLUMN_TYPE_LABEL = "label";
	public static final String COLUMN_TYPE_TEXT = "text";

	public static final String ALIGN_LEFT = "left";
	public static final String ALIGN_CENTER = "center";
	public static final String ALIGN_RIGHT = "right";

	private String type;
	private String title;
	private String field;
	private int width;
	private int rowspan;
	private int colspan;
	private String align;
	private boolean sortable;
	private boolean resizable;
	private boolean hidden;

	/**
	 * 基础列类型
	 * 
	 * @param type      列类型
	 * @param field     列字段
	 * @param width     列宽
	 * @param rowspan   行合并
	 * @param colspan   列合并
	 * @param align     对齐方式
	 * @param sortable  是否能排序
	 * @param resizable 是否能拖动列宽
	 * @param hidden    是否隐藏
	 */
	public DxDataGridAbstractColumn(String type, String field, String title, int width, int rowspan, int colspan, String align, boolean sortable, boolean resizable, boolean hidden) {
		super();
		this.type = type;
		this.field = field;
		this.title = title;
		this.width = width;
		this.rowspan = rowspan;
		this.colspan = colspan;
		this.align = align;
		this.sortable = sortable;
		this.resizable = resizable;
		this.hidden = hidden;
	}

	public String getType() {
		return type;
	}

	public String getField() {
		return field;
	}

	public int getWidth() {
		return width;
	}

	public int getRowspan() {
		return rowspan;
	}

	public int getColspan() {
		return colspan;
	}

	public String getAlign() {
		return align;
	}

	public boolean isSortable() {
		return sortable;
	}

	public boolean isResizable() {
		return resizable;
	}

	public boolean isHidden() {
		return hidden;
	}

	public String getTitle() {
		return title;
	}

}
