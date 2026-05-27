package cn.jpeach.frame.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GridData {
	private int total;
	private List<Map<String, Object>> rows;

	public GridData() {
		this.total = 0;
		this.rows = new ArrayList<Map<String, Object>>();
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<Map<String, Object>> getRows() {
		return rows;
	}

	public void setRows(List<Map<String, Object>> rows) {
		this.rows = rows;
	}
}
