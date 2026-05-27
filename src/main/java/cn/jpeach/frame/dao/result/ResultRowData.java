package cn.jpeach.frame.dao.result;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public class ResultRowData implements Serializable {
	private static final long serialVersionUID = 4123394291130441644L;
	private List<ResultFieldData> datas;
	private boolean update;
	private boolean insert;
	private String rownum = UUID.randomUUID().toString();

	public List<ResultFieldData> getDatas() {
		return datas;
	}

	public void setDatas(List<ResultFieldData> datas) {
		this.datas = datas;
	}

	public boolean isUpdate() {
		return update;
	}

	public void setUpdate(boolean update) {
		this.update = update;
	}

	public boolean isInsert() {
		return insert;
	}

	public void setInsert(boolean insert) {
		this.insert = insert;
	}

	public String getRownum() {
		return rownum;
	}
}
