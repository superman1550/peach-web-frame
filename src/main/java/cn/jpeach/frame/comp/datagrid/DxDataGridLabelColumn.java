package cn.jpeach.frame.comp.datagrid;

/**
 * label类型的DataGird列类型
 */
public class DxDataGridLabelColumn extends DxDataGridAbstractColumn {
	/**
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
	public DxDataGridLabelColumn(String field, String title, int width, int rowspan, int colspan, String align, boolean sortable, boolean resizable, boolean hidden) {
		super(DxDataGridAbstractColumn.COLUMN_TYPE_LABEL, field, title, width, rowspan, colspan, align, sortable, resizable, hidden);
	}
}
