package cn.jpeach.frame.dao;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import cn.jpeach.frame.core.Constant;
import cn.jpeach.frame.exception.FrameException;

public abstract class BaseDao {
	private DaoModel model;
	private Object[] parameters;
	private DaoResult result;
	private int current;
	private DaoTimer timer;
	private DataSource dataSource;
	private boolean load;

	BaseDao(String sql) {
		this(new DaoModel(sql));
	}

	BaseDao(DaoModel model) {
		super();
		this.model = model;
		this.load = false;
		this.timer = new DaoTimer(model);
	}

	DaoResult getResult() {
		return result;
	}

	DaoModel getModel() {
		return model;
	}

	public String getName() {
		return this.model.getName();
	}

	public Integer[] getParameterTypes() {
		List<Integer> list = new ArrayList<Integer>();
		for (int object : this.model.getParameters()) {
			list.add(object);
		}
		return list.toArray(new Integer[] {});
	}

	public int getRowCount() {
		return this.result.getRowCount();
	}

	public int getSelectRow() {
		return current;
	}

	public Object getRowValue(int rowIndex, String label) {
		return this.result.getRowValue(rowIndex, label);
	}

	public String getRowValueAsString(int row, String columnName) {
		Object value = this.getRowValue(row, columnName);
		if (value instanceof Timestamp) {
			return DateFormatUtils.format((Timestamp) value, Constant.DATE_FORMAT_PATTERN);
		} else {
			return value + "";
		}
	}

	public int getRowValueAsInt(int row, String columnName) {
		Object value = this.getRowValue(row, columnName);
		if (value instanceof Timestamp) {
			throw new FrameException("Timestamp类型不能转成int");
		} else if (value instanceof BigDecimal) {
			BigDecimal decimal = (BigDecimal) value;
			return decimal.intValue();
		} else {
			return NumberUtils.toInt(value + "", 0);
		}
	}

	public double getRowValueAsDouble(int row, String columnName) {
		Object value = this.getRowValue(row, columnName);
		if (value instanceof Timestamp) {
			throw new FrameException("Timestamp类型不能转成Double");
		} else if (value instanceof BigDecimal) {
			BigDecimal decimal = (BigDecimal) value;
			return decimal.doubleValue();
		} else {
			return NumberUtils.toDouble(value + "", 0);
		}
	}

	public float getRowValueAsFloat(int row, String columnName) {
		Object value = this.getRowValue(row, columnName);
		if (value instanceof Timestamp) {
			throw new FrameException("Timestamp类型不能转成Double");
		} else if (value instanceof BigDecimal) {
			BigDecimal decimal = (BigDecimal) value;
			return decimal.floatValue();
		} else {
			return NumberUtils.toFloat(value + "", 0);
		}
	}

	public long getRowValueAsLong(int row, String columnName) {
		Object value = this.getRowValue(row, columnName);
		if (value instanceof Timestamp) {
			Timestamp timestamp = (Timestamp) value;
			return timestamp.getTime();
		} else if (value instanceof BigDecimal) {
			BigDecimal decimal = (BigDecimal) value;
			return decimal.longValue();
		} else {
			return NumberUtils.toLong(value + "", 0);
		}
	}

	public boolean isLoad() {
		return this.load;
	}

	public void setParameters(Object[] parameters) {
		this.parameters = parameters;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void reload() {
		try {
			this.timer.reset();
			this.current = 0;
			this.result = new DaoResult(StaticJdbcManager.query(this.dataSource, this.model, this.parameters));
			this.load = true;
			this.timer.reload(this.parameters, this.getRowCount());
		} catch (SQLException e) {
			throw new FrameException("查询异常", e);
		}
	}

	public boolean commit() {
		try {
			return StaticJdbcManager.commit(this.dataSource, this.model, this.result, this.timer);
		} catch (Exception e) {
			throw new FrameException("保存异常", e);
		}
	}

	public void absolute(int rowIndex) {
		this.current = rowIndex;
	}

	public void insertRow(int index) {
		this.result.insertRow(index, this.model.getFields());
	}

	public void insertRow() {
		this.result.insertRow(this.model.getFields());
	}

	public void deleteRow(int rowIndex) {
		this.result.deleteRow(rowIndex);
	}

	public void deleteAllRow() {
		this.result.deleteRowAll();
	}

	public void updateRow(int rowIndex, String label, Object value) {
		this.result.updateRow(rowIndex, label, value);
	}
}
