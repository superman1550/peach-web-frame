package cn.jpeach.frame.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import cn.jpeach.frame.entity.DocumentElement;

class StaticDaoModelBuild {
	private static final String ELEMENT_NAME_SQL = "sql";
	private static final String ELEMENT_NAME_TABLES = "tables";
	private static final String ELEMENT_NAME_FIELDS = "fields";
	private static final String ELEMENT_NAME_PARAMETERS = "parameters";
	private static final String ELEMENT_ATTRIBUTE_NAME = "name";
	private static final String ELEMENT_ATTRIBUTE_INSERT = "insert";
	private static final String ELEMENT_ATTRIBUTE_UPDATE = "update";
	private static final String ELEMENT_ATTRIBUTE_DELETE = "delete";
	private static final String ELEMENT_ATTRIBUTE_TABLE = "table";
	private static final String ELEMENT_ATTRIBUTE_TYPE = "type";

	static DaoModel build(DocumentElement element) {
		DaoModel model = new DaoModel();
		model.setName(element.getAttribute(ELEMENT_ATTRIBUTE_NAME));
		for (int i = 0; i < element.childrenCount(); i++) {
			DocumentElement child = element.getChild(i);
			if (ELEMENT_NAME_SQL.equalsIgnoreCase(child.getName())) {
				model.setSql(child.getText());
			} else if (ELEMENT_NAME_TABLES.equalsIgnoreCase(child.getName())) {
				model.setTables(buildTables(child));
			} else if (ELEMENT_NAME_FIELDS.equalsIgnoreCase(child.getName())) {
				model.setFields(buildFields(child));
			} else if (ELEMENT_NAME_PARAMETERS.equalsIgnoreCase(child.getName())) {
				model.setParameters(buildParameters(child));
			}
		}
		return model;
	}

	private static DaoTable[] buildTables(DocumentElement element) {
		List<DaoTable> list = new ArrayList<DaoTable>();
		for (int i = 0; i < element.childrenCount(); i++) {
			DocumentElement child = element.getChild(i);
			DaoTable table = new DaoTable();
			table.setTable(child.getAttribute(ELEMENT_ATTRIBUTE_NAME).toLowerCase());
			table.setInsert(child.getAttributeAsBool(ELEMENT_ATTRIBUTE_INSERT, false));
			table.setUpdate(child.getAttributeAsBool(ELEMENT_ATTRIBUTE_UPDATE, false));
			table.setDelete(child.getAttributeAsBool(ELEMENT_ATTRIBUTE_DELETE, false));
			table.setPrimarys(buildPrimarys(child));
			list.add(table);
		}
		return list.toArray(new DaoTable[] {});
	}

	private static String[] buildPrimarys(DocumentElement element) {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < element.childrenCount(); i++) {
			DocumentElement child = element.getChild(i);
			list.add(child.getText().toLowerCase());
		}
		return list.toArray(new String[] {});
	}

	private static DaoField[] buildFields(DocumentElement element) {
		List<DaoField> list = new ArrayList<DaoField>();
		for (int i = 0; i < element.childrenCount(); i++) {
			DocumentElement child = element.getChild(i);
			DaoField field = new DaoField();
			field.setName(child.getAttribute(ELEMENT_ATTRIBUTE_NAME).toLowerCase());
			String tables = child.getAttribute(ELEMENT_ATTRIBUTE_TABLE);
			if (StringUtils.isEmpty(tables)) {
				field.setTable(new String[] {});
			} else {
				field.setTable(tables.toLowerCase().split(","));
			}
			field.setType(NumberUtils.toInt(child.getAttribute(ELEMENT_ATTRIBUTE_TYPE), 12));
			list.add(field);
		}
		return list.toArray(new DaoField[] {});
	}

	private static int[] buildParameters(DocumentElement element) {
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < element.childrenCount(); i++) {
			DocumentElement child = element.getChild(i);
			list.add(NumberUtils.toInt(child.getText()));
		}
		int[] is = new int[list.size()];
		for (int i = 0; i < list.size(); i++) {
			is[i] = list.get(i);
		}
		return is;
	}

}
