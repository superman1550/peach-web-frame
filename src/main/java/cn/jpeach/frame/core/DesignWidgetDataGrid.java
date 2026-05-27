package cn.jpeach.frame.core;

import java.util.ArrayList;
import java.util.List;

import cn.jpeach.frame.comp.PxDataGrid;
import cn.jpeach.frame.comp.datagrid.DxDataGridAbstractColumn;
import cn.jpeach.frame.comp.datagrid.DxDataGridLabelColumn;
import cn.jpeach.frame.comp.datagrid.DxDataGridTextColumn;
import cn.jpeach.frame.entity.DocumentElement;

class DesignWidgetDataGrid extends DesignWidgetFormComp<PxDataGrid> {
	@Override
	String getTag() {
		return "PxDataGird";
	}

	@Override
	PxDataGrid createComponent(WebForm form, DocumentElement element) {
		PxDataGrid panel = new PxDataGrid(form);
		this.init(panel, element);
		this.initColumns(panel, element);
		return panel;
	}

	@Override
	boolean hasChild() {
		return false;
	}

	void init(PxDataGrid comp, DocumentElement element) {
		super.init(comp, element);
		this.setBoolProperty(comp, element, Constant.PRO_FIT_COLUMNS, false);
		this.setBoolProperty(comp, element, Constant.PRO_STRIPED, true);
		this.setBoolProperty(comp, element, Constant.PRO_SHOW_FOOTER, false);
		this.setIntProperty(comp, element, Constant.PRO_PAGE_SIZE, 50);
		this.setBoolProperty(comp, element, Constant.PRO_PAGINATION, false);
		this.setBoolProperty(comp, element, Constant.PRO_ROWNUMBERS, false);
		this.setBoolProperty(comp, element, Constant.PRO_FIT, false);
		this.setStrProperty(comp, element, Constant.PRO_VIEW, PxDataGrid.VIEW_DEFAULT);
		this.setStrProperty(comp, element, Constant.PRO_DAO);
	}

	private void initColumns(PxDataGrid panel, DocumentElement element) {
		List<List<DxDataGridAbstractColumn>> columns = new ArrayList<List<DxDataGridAbstractColumn>>();
		for (int i = 0; i < element.childrenCount(); i++) {
			columns.add(this.createColumnRow(element.getChild(i)));
		}
		panel.setColumns(columns);
	}

	private List<DxDataGridAbstractColumn> createColumnRow(DocumentElement element) {
		List<DxDataGridAbstractColumn> columns = new ArrayList<DxDataGridAbstractColumn>();
		for (int i = 0; i < element.childrenCount(); i++) {
			DocumentElement child = element.getChild(i);
			String type = child.getAttribute(Constant.PRO_TYPE);
			if (DxDataGridAbstractColumn.COLUMN_TYPE_TEXT.equalsIgnoreCase(type)) {
				columns.add(this.createTextColumn(child));
			} else {
				columns.add(this.createLabelColumn(child));
			}
		}
		return columns;
	}

	private DxDataGridAbstractColumn createTextColumn(DocumentElement element) {
		int width = element.getAttributeAsInt(Constant.PRO_WIDTH, 120);
		String field = element.getAttributeAsString(Constant.PRO_FIELD, "").toLowerCase();
		String title = element.getText();
		int rowspan = element.getAttributeAsInt(Constant.PRO_ROWSPAN, 1);
		int colspan = element.getAttributeAsInt(Constant.PRO_COLSPAN, 1);
		String align = element.getAttributeAsString(Constant.PRO_ALIGN, DxDataGridAbstractColumn.ALIGN_LEFT);
		boolean sortable = element.getAttributeAsBool(Constant.PRO_SORTABLE, false);
		boolean resizable = element.getAttributeAsBool(Constant.PRO_RESIZABLE, false);
		boolean hidden = element.getAttributeAsBool(Constant.PRO_HIDDEN, false);
		return new DxDataGridTextColumn(field, title, width, rowspan, colspan, align, sortable, resizable, hidden);
	}

	private DxDataGridAbstractColumn createLabelColumn(DocumentElement element) {
		int width = element.getAttributeAsInt(Constant.PRO_WIDTH, 120);
		String field = element.getAttributeAsString(Constant.PRO_FIELD, "").toLowerCase();
		String title = element.getText();
		int rowspan = element.getAttributeAsInt(Constant.PRO_ROWSPAN, 1);
		int colspan = element.getAttributeAsInt(Constant.PRO_COLSPAN, 1);
		String align = element.getAttributeAsString(Constant.PRO_ALIGN, DxDataGridAbstractColumn.ALIGN_LEFT);
		boolean sortable = element.getAttributeAsBool(Constant.PRO_SORTABLE, false);
		boolean resizable = element.getAttributeAsBool(Constant.PRO_RESIZABLE, false);
		boolean hidden = element.getAttributeAsBool(Constant.PRO_HIDDEN, false);
		return new DxDataGridLabelColumn(field, title, width, rowspan, colspan, align, sortable, resizable, hidden);
	}
}
