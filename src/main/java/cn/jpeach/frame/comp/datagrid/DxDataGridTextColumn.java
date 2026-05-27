package cn.jpeach.frame.comp.datagrid;

public class DxDataGridTextColumn extends DxDataGridAbstractColumn {

	public DxDataGridTextColumn(String field, String title, int width, int rowspan, int colspan, String align, boolean sortable, boolean resizable, boolean hidden) {
		super(DxDataGridAbstractColumn.COLUMN_TYPE_TEXT, field, title, width, rowspan, colspan, align, sortable, resizable, hidden);
	}

}
