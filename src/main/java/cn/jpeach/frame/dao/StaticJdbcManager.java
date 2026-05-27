package cn.jpeach.frame.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;

import cn.jpeach.frame.dao.result.ResultArrayData;
import cn.jpeach.frame.dao.result.ResultFieldData;
import cn.jpeach.frame.dao.result.ResultRowData;
import cn.jpeach.frame.exception.FrameException;

class StaticJdbcManager {

	/**
	 * 查询数据集
	 * 
	 * @param conn
	 * @param model
	 * @param parameterValues
	 * @return
	 * @throws SQLException
	 */
	static ResultArrayData query(DataSource source, DaoModel model, Object[] parameterValues) throws SQLException {
		if (source == null || source.isClose()) {
			source = new DaoDataSource();
			try (Connection connection = source.getConnection()) {
				return queryResult(connection, model, parameterValues);
			}
		} else {
			return queryResult(source.getConnection(), model, parameterValues);
		}

	}

	/**
	 * 提交数据集
	 * 
	 * @param connection
	 * @param model
	 * @param result
	 * @return
	 * @throws SQLException
	 */
	static boolean commit(DataSource source, DaoModel model, DaoResult result, DaoTimer timer) throws SQLException {
		if (source == null || source.isClose()) {
			source = new DaoDataSource();
			try (Connection connection = source.getConnection()) {
				connection.setAutoCommit(false);
				try {
					timer.beginTransaction();
					commitResult(connection, model, result, timer);
					connection.commit();
					resetResult(result);
					timer.commitTransaction();
					return true;
				} catch (DaoExeption e) {
					connection.rollback();
					timer.rollbackTransaction();
					return false;
				} catch (SQLException e) {
					timer.printError("提交异常：", e);
					connection.rollback();
					timer.rollbackTransaction();
					return false;
				}
			}
		} else {
			try {
				commitResult(source.getConnection(), model, result, timer);
				return true;
			} catch (DaoExeption e) {
				return false;
			} catch (SQLException e) {
				timer.printError("提交异常：", e);
				return false;
			}
		}
	}

	static void resetResult(DaoResult result) {
		result.getData().getDeletesRows().clear();
		List<ResultRowData> rows = result.getData().getOriginalrows();
		for (ResultRowData row : rows) {
			row.setInsert(false);
			row.setUpdate(false);
			List<ResultFieldData> datas = row.getDatas();
			for (ResultFieldData data : datas) {
				data.setOldValue(data.getNewValue());
				data.setUpdate(false);
			}
		}
	}

	private static ResultArrayData queryResult(Connection conn, DaoModel model, Object[] parameterValues) throws SQLException {
		String sql = model.getSql();
		int[] parameterTypes = model.getParameters();
		DaoField[] fields = model.getFields();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		if (parameterTypes.length > 0 && parameterValues.length != parameterTypes.length) {
			throw new FrameException(String.format("申明的参数和查询的的参数个数不一致：%s-%s", parameterTypes.length, parameterValues.length));
		}

		for (int i = 0; i < parameterTypes.length; i++) {
			pstmt.setObject(i + 1, parameterValues[i], parameterTypes[i]);
		}
		// 4. 执行查询，获得结果集
		ResultSet rs = pstmt.executeQuery();
		List<ResultRowData> rows = new ArrayList<ResultRowData>();
		while (rs.next()) {
			List<ResultFieldData> rowDatas = new ArrayList<ResultFieldData>();
			for (int i = 0; i < fields.length; i++) {
				Object value = rs.getObject(fields[i].getName());
				ResultFieldData fieldData = new ResultFieldData();
				fieldData.setLabel(fields[i].getName());
				fieldData.setOldValue(value);
				fieldData.setNewValue(value);
				fieldData.setType(fields[i].getType());
				fieldData.setUpdate(false);
				rowDatas.add(fieldData);
			}
			ResultRowData row = new ResultRowData();
			row.setDatas(rowDatas);
			row.setInsert(false);
			row.setUpdate(false);
			rows.add(row);
		}
		ResultArrayData arrayData = new ResultArrayData();
		arrayData.setOriginalrows(rows);
		arrayData.setDeletesRows(new ArrayList<ResultRowData>());
		return arrayData;
	}

	/**
	 * 提交数据库
	 * 
	 * @param conn
	 * @param model
	 * @param result
	 * @param timer
	 * @return
	 * @throws SQLException
	 * @throws DaoExeption
	 */
	private static void commitResult(Connection conn, DaoModel model, DaoResult result, DaoTimer timer) throws SQLException, DaoExeption {
		executeDelete(conn, model, result.getData().getDeletesRows(), timer);
		executeInsert(conn, model, result.getData().getOriginalrows(), timer);
		executeUpdate(conn, model, result.getData().getOriginalrows(), timer);
	}

	/**
	 * 执行删除操作
	 * 
	 * @param conn
	 * @param model
	 * @param rows
	 * @param timer
	 * @throws SQLException
	 * @throws DaoExeption
	 */
	private static void executeDelete(Connection conn, DaoModel model, List<ResultRowData> rows, DaoTimer timer) throws SQLException, DaoExeption {
		DaoTable[] tables = model.getTables();
		for (DaoTable table : tables) {
			executeDeleteTable(conn, table, rows, timer);
		}
	}

	/**
	 * 执行表删除数据
	 * 
	 * @param conn
	 * @param table
	 * @param rows
	 * @param timer
	 * @throws SQLException
	 * @throws DaoExeption
	 */
	private static void executeDeleteTable(Connection conn, DaoTable table, List<ResultRowData> rows, DaoTimer timer) throws SQLException, DaoExeption {
		String sql = String.format("delete %s where %s", table.getTable(), createPrimarySql(table));
		try (PreparedStatement statement = conn.prepareStatement(sql)) {
			for (int i = 0; i < rows.size(); i++) {
				List<String> parms = createPrimaryValue(statement, table, rows.get(i), 0);
				timer.printSql(sql, parms);
				if (statement.executeUpdate() == 0) {
					throw new DaoExeption("没有数据被删除!");
				}
			}
		}
	}

	/**
	 * 执行插入数据
	 * 
	 * @param conn
	 * @param model
	 * @param rows
	 * @param timer
	 * @throws SQLException
	 * @throws DaoExeption
	 */
	private static void executeInsert(Connection conn, DaoModel model, List<ResultRowData> rows, DaoTimer timer) throws SQLException, DaoExeption {
		DaoTable[] tables = model.getTables();
		DaoField[] fields = model.getFields();
		for (DaoTable table : tables) {
			executeInsertTable(conn, table, fields, rows, timer);
		}
	}

	private static void executeInsertTable(Connection conn, DaoTable table, DaoField[] fields, List<ResultRowData> rows, DaoTimer timer) throws SQLException, DaoExeption {
		String[] fieldNames = createFieldsSql(table.getTable(), fields);
		String[] placeHolds = createPlacesSql(fieldNames);
		String sql = String.format("insert into %s (%s) values (%s)", table.getTable(), String.join(",", fieldNames), String.join(",", placeHolds));
		try (PreparedStatement statement = conn.prepareStatement(sql)) {
			for (int i = 0; i < rows.size(); i++) {
				ResultRowData row = rows.get(i);
				if (row.isInsert()) {
					List<String> parms = createFieldsValue(statement, row, fieldNames);
					timer.printSql(sql, parms);
					if (statement.executeUpdate() == 0) {
						throw new DaoExeption("没有数据被插入!");
					}
				}
			}
		}
	}

	private static void executeUpdate(Connection conn, DaoModel model, List<ResultRowData> rows, DaoTimer timer) throws SQLException, DaoExeption {
		DaoTable[] tables = model.getTables();
		DaoField[] fields = model.getFields();
		for (DaoTable table : tables) {
			executeUpdateTable(conn, table, fields, rows, timer);
		}
	}

	private static void executeUpdateTable(Connection conn, DaoTable table, DaoField[] fields, List<ResultRowData> rows, DaoTimer timer) throws SQLException, DaoExeption {
		for (int i = 0; i < rows.size(); i++) {
			ResultRowData row = rows.get(i);
			if (!row.isInsert() && row.isUpdate()) {
				String fieldSql = createUpdateFieldsSql(table.getTable(), fields, row.getDatas());
				String primarySql = createPrimarySql(table);
				String sql = String.format("update %s set %s where %s", table.getTable(), fieldSql, primarySql);
				try (PreparedStatement statement = conn.prepareStatement(sql)) {
					List<String> fieldValues = createUpdateValue(statement, table.getTable(), fields, row.getDatas());
					List<String> primaryValues = createPrimaryValue(statement, table, row, fieldValues.size());
					fieldValues.addAll(primaryValues);
					timer.printSql(sql, fieldValues);
					if (statement.executeUpdate() == 0) {
						throw new DaoExeption("没有数据被修改!");
					}
				}
			}
		}
	}

	/**
	 * 拼接sql语句修改字段
	 * 
	 * @param table
	 * @param fields
	 * @param datas
	 * @return
	 */
	private static String createUpdateFieldsSql(String table, DaoField[] fields, List<ResultFieldData> datas) {
		List<String> fieldNames = new ArrayList<String>();
		for (DaoField field : fields) {
			if (ArrayUtils.contains(field.getTable(), table)) {
				fieldNames.add(field.getName());
			}
		}

		List<String> updateFields = new ArrayList<String>();
		for (ResultFieldData fieldData : datas) {
			if (fieldData.isUpdate() && fieldNames.contains(fieldData.getLabel())) {
				updateFields.add(fieldData.getLabel() + "=?");
			}
		}
		return String.join(",", updateFields);
	}

	private static List<String> createUpdateValue(PreparedStatement statement, String table, DaoField[] fields, List<ResultFieldData> datas) throws SQLException {
		List<String> fieldNames = new ArrayList<String>();
		for (DaoField field : fields) {
			if (ArrayUtils.contains(field.getTable(), table)) {
				fieldNames.add(field.getName());
			}
		}

		List<String> showValues = new ArrayList<String>();
		for (ResultFieldData data : datas) {
			if (data.isUpdate() && fieldNames.contains(data.getLabel())) {
				int i = showValues.size();
				statement.setObject(i + 1, data.getNewValue(), data.getType());
				showValues.add(data.getNewValue() + "");
			}
		}
		return showValues;
	}

	/**
	 * 拼接sql语句插入字段
	 * 
	 * @param table
	 * @param fields
	 * @return
	 */
	private static String[] createFieldsSql(String table, DaoField[] fields) {
		List<String> fieldNames = new ArrayList<String>();
		for (DaoField field : fields) {
			if (ArrayUtils.contains(field.getTable(), table)) {
				fieldNames.add(field.getName());
			}
		}
		return fieldNames.toArray(new String[] {});
	}

	/**
	 * 拼接sql语句插入字段占位
	 * 
	 * @param table
	 * @param fields
	 * @return
	 */
	private static String[] createPlacesSql(String[] fieldNames) {
		List<String> places = new ArrayList<String>();
		for (int i = 0; i < fieldNames.length; i++) {
			places.add("?");
		}
		return places.toArray(new String[] {});
	}

	private static List<String> createFieldsValue(PreparedStatement statement, ResultRowData row, String[] fieldNames) throws DaoExeption, SQLException {
		List<String> showValues = new ArrayList<String>();
		for (int i = 0; i < fieldNames.length; i++) {
			ResultFieldData data = findFieldData(row.getDatas(), fieldNames[i]);
			statement.setObject(i + 1, data.getNewValue(), data.getType());
			showValues.add(data.getNewValue() + "");
		}
		return showValues;
	}

	/**
	 * 拼接sql语句主键部分
	 * 
	 * @param table
	 * @return
	 */
	private static String createPrimarySql(DaoTable table) {
		List<String> ps = new ArrayList<String>();
		for (String p : table.getPrimarys()) {
			ps.add(p + "=?");
		}
		return String.join(",", ps);
	}

	/**
	 * 设置PreparedStatement主键参数
	 * 
	 * @param statement
	 * @param table
	 * @param row
	 * @return
	 * @throws SQLException
	 * @throws DaoExeption
	 */
	private static List<String> createPrimaryValue(PreparedStatement statement, DaoTable table, ResultRowData row, int offset) throws SQLException, DaoExeption {
		String[] primarys = table.getPrimarys();
		List<String> showValues = new ArrayList<String>();
		for (int i = 0; i < primarys.length; i++) {
			ResultFieldData data = findFieldData(row.getDatas(), primarys[i]);
			statement.setObject(i + offset + 1, data.getOldValue(), data.getType());
			showValues.add(data.getOldValue() + "");
		}
		return showValues;
	}

	private static ResultFieldData findFieldData(List<ResultFieldData> datas, String label) throws DaoExeption {
		for (ResultFieldData field : datas) {
			if (field.getLabel().equalsIgnoreCase(label)) {
				return field;
			}
		}
		throw new DaoExeption(String.format("未找到列[]", label));
	}

	static class ResultMeta {
		private String label;
		private int type;

		protected ResultMeta(String label, int type) {
			this.label = label;
			this.type = type;
		}

		String getLabel() {
			return label;
		}

		int getType() {
			return type;
		}

	}

}
