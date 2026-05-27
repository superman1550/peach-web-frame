package cn.jpeach.frame.comp;

import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;

import cn.jpeach.frame.action.SelectListener;
import cn.jpeach.frame.comp.datagrid.DxDataGridAbstractColumn;
import cn.jpeach.frame.core.Constant;
import cn.jpeach.frame.core.WebForm;
import cn.jpeach.frame.core.WebWidget;
import cn.jpeach.frame.dao.DataDao;
import cn.jpeach.frame.dao.StaticDaoDataManager;
import cn.jpeach.frame.entity.DataParameter;
import cn.jpeach.frame.entity.GridData;

public final class PxDataGrid extends WebWidget {
	public static final String VIEW_DEFAULT = "DefaultView";
	public static final String VIEW_BUFFER = "BufferView";
	public static final String VIEW_VIRTUAL_SCROLL = "VirtualScrollView";
	
	private List<List<DxDataGridAbstractColumn>> columns;

	public PxDataGrid(WebForm form) {
		super(form);
	}

	/**
	 * 设置数据列
	 * 
	 * @param columns
	 */
	public void setColumns(List<List<DxDataGridAbstractColumn>> columns) {
		this.columns = columns;
		this.setAppObject(Constant.PRO_COLUMNS, columns);
	}

	/**
	 * 获取单元格
	 * 
	 * @param row
	 * @param col
	 * @return
	 */
	public DxDataGridAbstractColumn getColumn(int row, int col) {
		return columns.get(row).get(col);
	}

	/**
	 * 获取单元格值
	 * 
	 * @param row
	 * @param col
	 * @return
	 */
	public Object getRowValue(int row, String columnName) {
		DataDao dao = this.getDao();
		return dao.getRowValue(row, columnName);
	}

	public String getRowValueAsString(int row, String columnName) {
		DataDao dao = this.getDao();
		return dao.getRowValueAsString(row, columnName);
	}

	public int getSelected() {
		return this.getAppInt(Constant.PRO_SELECTED);
	}

	/**
	 * 加载数据
	 */
	public void reload() {
		DataDao dao = this.getDao();
		dao.reload();
		this.addChange(Constant.ACTION_RELOAD, new Object[] {});
	}

	public boolean commit() {
		DataDao dao = this.getDao();
		return dao.commit();
	}

	/**
	 * 设置加载数据参数
	 * 
	 * @param parameters
	 */
	public void setParameters(Object[] parameters) {
		DataDao dao = this.getDao();
		dao.setParameters(parameters);
	}

	public void selectRow(int index) {
		DataDao dao = this.getDao();
		if (dao.isLoad()) {
			this.setAppInt(Constant.PRO_SELECTED, index);
			this.addChange(Constant.ACTION_SELECT, new Object[] { index });
		}
	}

	/**
	 * 插入一条新行
	 * 
	 * @param index
	 */
	public void insertRow(int index) {
		DataDao dao = this.getDao();
		if (dao.isLoad()) {
			dao.insertRow(index);
			Object row = StaticDaoDataManager.toData(dao, index);
			this.addChange(Constant.ACTION_INSERT_ROW, new Object[] { index, row });
		}
	}

	public void updateRow(int rowIndex, String columnName, Object value) {
		DataDao dao = this.getDao();
		if (dao.isLoad()) {
			dao.updateRow(rowIndex, columnName, value);
			Object row = StaticDaoDataManager.toData(dao, rowIndex);
			this.addChange(Constant.ACTION_UPDATE_ROW, new Object[] { rowIndex, row });
		}
	}

	public void deleteRow(int rowIndex) {
		DataDao dao = this.getDao();
		if (dao.isLoad()) {
			dao.deleteRow(rowIndex);
			this.addChange(Constant.ACTION_DELETE_ROW, new Object[] { rowIndex });
		}
	}

	public void addSelectListener(SelectListener listener) {
		this.addListener(listener);
	}

	@Override
	protected GridData remoteData(DataParameter parameter) {
		DataDao dao = this.getDao();
		if (dao.isLoad()) {
			return StaticDaoDataManager.toData(dao, parameter);
		} else {
			return new GridData();
		}
	}

	@Override
	protected void setAppProperty(String key, Object[] values) {
		if (Constant.PRO_DATA.equalsIgnoreCase(key)) {
			DataDao dao = this.getDao();
			if (values.length > 0 && dao.isLoad()) {
				StaticDaoDataManager.updateDao(dao, values[0]);
			}
		} else if (Constant.PRO_SELECTED.equalsIgnoreCase(key)) {
			DataDao dao = this.getDao();
			int row = NumberUtils.toInt(values[0] + "", -1);
			this.setAppInt(Constant.PRO_SELECTED, row);
			dao.absolute(row);
		}
	}

	private DataDao getDao() {
		return this.getDao(this.getAppString(Constant.PRO_DAO));
	}
}
