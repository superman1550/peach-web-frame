package cn.jpeach.frame.dao;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.math.NumberUtils;

import cn.jpeach.frame.dao.result.ResultArrayData;
import cn.jpeach.frame.dao.result.ResultFieldData;
import cn.jpeach.frame.dao.result.ResultRowData;

class DaoResult {
	private ResultArrayData data;

	DaoResult(ResultArrayData data) {
		super();
		this.data = data;
	}

	ResultArrayData getData() {
		return data;
	}

	int getRowCount() {
		return data.getOriginalrows().size();
	}

	void insertRow(int index, DaoField[] fields) {
		List<ResultRowData> originalrows = this.data.getOriginalrows();
		originalrows.add(index, this.createNewRow(fields));
	}

	void insertRow(DaoField[] fields) {
		List<ResultRowData> originalrows = this.data.getOriginalrows();
		originalrows.add(this.createNewRow(fields));
	}

	void deleteRow(int index) {
		List<ResultRowData> originalrows = this.data.getOriginalrows();
		List<ResultRowData> deletesRows = this.data.getDeletesRows();
		deletesRows.add(originalrows.remove(index));
	}

	void deleteRowAll() {
		List<ResultRowData> originalrows = this.data.getOriginalrows();
		List<ResultRowData> deletesRows = this.data.getDeletesRows();
		for (int i = 0; i < originalrows.size(); i++) {
			deletesRows.add(this.data.getOriginalrows().get(i));
		}
		originalrows.clear();
	}

	void updateRow(int rowIndex, String label, Object value) {
		ResultRowData rowData = this.data.getOriginalrows().get(rowIndex);
		List<ResultFieldData> fieldDatas = rowData.getDatas();
		for (int i = 0; i < fieldDatas.size(); i++) {
			ResultFieldData fieldData = fieldDatas.get(i);
			if (fieldData.getLabel().equalsIgnoreCase(label)) {
				fieldData.setNewValue(value);
				fieldData.setUpdate(true);
				break;
			}
		}
	}

	Object getRowValue(int rowIndex, String label) {
		ResultRowData rowData = this.data.getOriginalrows().get(rowIndex);
		List<ResultFieldData> fieldDatas = rowData.getDatas();
		for (int i = 0; i < fieldDatas.size(); i++) {
			ResultFieldData fieldData = fieldDatas.get(i);
			if (fieldData.getLabel().equalsIgnoreCase(label)) {
				return fieldData.getNewValue();
			}
		}
		return null;
	}

	/**
	 * 前端同步
	 * 
	 * @param map
	 * @param rmKey
	 */
	void update(Map<String, Object> map, String rmKey) {
		for (ResultRowData row : this.data.getOriginalrows()) {
			if (row.getRownum().equals(map.get(rmKey))) {
				updateRow(row, map);
			}
		}
	}

	private ResultRowData createNewRow(DaoField[] fields) {
		List<ResultFieldData> datas = new ArrayList<ResultFieldData>();
		if (ArrayUtils.isNotEmpty(fields)) {
			for (int i = 0; i < fields.length; i++) {
				ResultFieldData data = new ResultFieldData();
				data.setLabel(fields[i].getName());
				data.setType(fields[i].getType());
				datas.add(data);
			}
		}
		ResultRowData row = new ResultRowData();
		row.setInsert(true);
		row.setDatas(datas);
		return row;
	}

	/**
	 * 前端同步行
	 * 
	 * @param row
	 * @param map
	 */
	private void updateRow(ResultRowData row, Map<String, Object> map) {
		List<ResultFieldData> datas = row.getDatas();
		for (ResultFieldData fieldData : datas) {
			String label = fieldData.getLabel();
			if (fieldData.getType() == Types.TIMESTAMP || fieldData.getType() == Types.DATE) {
				Object val = map.get(label);
				Timestamp value = (Timestamp) fieldData.getNewValue();
				long longValue = 0L;
				if (val instanceof Long) {
					longValue = (long) val;
				} else {
					longValue = NumberUtils.toLong(val + "", 0);
				}
				if (value == null || value.getTime() != longValue) {
					fieldData.setNewValue(new Timestamp(longValue));
					fieldData.setUpdate(true);
					row.setUpdate(true);
				}
			} else if (fieldData.getType() == Types.NUMERIC) {
				Object val = map.get(label);
				BigDecimal value = (BigDecimal) fieldData.getNewValue();
				Number numberValue = 0;
				if (val instanceof Number) {
					numberValue = (Number) val;
				} else {
					numberValue = NumberUtils.toDouble(val + "", 0);
				}
				if (value == null || value.compareTo(BigDecimal.valueOf(numberValue.doubleValue())) != 0) {
					fieldData.setNewValue(new BigDecimal(numberValue.doubleValue()));
					fieldData.setUpdate(true);
					row.setUpdate(true);
				}
			} else if (fieldData.getType() == Types.VARCHAR) {
				String val = map.get(label) + "";
				String value = (String) fieldData.getNewValue();
				if (value == null || !value.equals(val)) {
					fieldData.setNewValue(val);
					fieldData.setUpdate(true);
					row.setUpdate(true);
				}
			} else {
				fieldData.setNewValue(map.get(label));
				fieldData.setUpdate(true);
				row.setUpdate(true);
			}
		}
	}

}
