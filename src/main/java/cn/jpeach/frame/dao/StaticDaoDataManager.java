package cn.jpeach.frame.dao;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import cn.jpeach.frame.dao.result.ResultArrayData;
import cn.jpeach.frame.dao.result.ResultFieldData;
import cn.jpeach.frame.dao.result.ResultRowData;
import cn.jpeach.frame.entity.DataParameter;
import cn.jpeach.frame.entity.GridData;
import cn.jpeach.frame.exception.FrameException;

public final class StaticDaoDataManager {
	static final String rownum = "_pui_rownum_id";

	/**
	 * 获取某行的前端返回信息
	 * 
	 * @param dao
	 * @param index
	 * @return
	 */
	public static Map<String, Object> toData(BaseDao dao, int index) {
		ResultRowData rowData = dao.getResult().getData().getOriginalrows().get(index);
		return rowData(rowData, dao.getModel().getFields());
	}

	/**
	 * 获取全部的前端返回信息
	 * 
	 * @param dao
	 * @param parameter
	 * @return
	 */
	public static GridData toData(BaseDao dao, DataParameter parameter) {
		DaoResult result = dao.getResult();
		DaoModel model = dao.getModel();
		return toGridData(result.getData(), model.getFields(), parameter);
	}

	/**
	 * 同步修改
	 * 
	 * @param object
	 */
	public static void updateDao(BaseDao dao, Object object) {
		try {

			@SuppressWarnings("unchecked")
			Map<String, List<Map<String, Object>>> reqData = (Map<String, List<Map<String, Object>>>) object;
			updateDaoRows(dao, reqData.get("inserted"));
			updateDaoRows(dao, reqData.get("updated"));
		} catch (Exception e) {
			throw new FrameException("修改失敗", e);
		}
	}

	private static GridData toGridData(ResultArrayData array, DaoField[] fields, DataParameter parameter) {
		GridData data = new GridData();
		List<ResultRowData> rows = array.getOriginalrows();
		data.setTotal(rows.size());
		data.setRows(mapList(rows, fields, parameter));
		return data;
	}

	private static List<Map<String, Object>> mapList(List<ResultRowData> rowDatas, DaoField[] fields, DataParameter parameter) {
		List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
		int page = parameter.getPage() == null ? 1 : parameter.getPage();
		int rows = parameter.getRows() == null ? rowDatas.size() : parameter.getRows();
		for (int i = (page - 1) * rows; i < Math.min(rowDatas.size(), page * rows); i++) {
			maps.add(rowData(rowDatas.get(i), fields));
		}
		return maps;
	}

	private static Map<String, Object> rowData(ResultRowData row, DaoField[] fields) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<ResultFieldData> datas = row.getDatas();
		for (int i = 0; i < datas.size(); i++) {
			ResultFieldData data = datas.get(i);
			map.put(data.getLabel().toLowerCase(), toString(data.getNewValue(), data.getType()));
		}
		map.put(rownum, row.getRownum());
		return map;
	}

	private static Object toString(Object value, int type) {
		if (value == null) {
			return StringUtils.EMPTY;
		}
		if (value instanceof BigDecimal) {
			return value.toString();
		} else if (value instanceof Timestamp) {
			Timestamp timestamp = (Timestamp) value;
			return timestamp.getTime();
		} else {
			return value.toString();
		}
	}

	private static void updateDaoRows(BaseDao dao, List<Map<String, Object>> rows) {
		for (Map<String, Object> map : rows) {
			dao.getResult().update(map, rownum);
		}
	}
}
