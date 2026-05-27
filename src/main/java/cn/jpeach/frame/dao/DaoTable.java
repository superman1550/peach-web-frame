package cn.jpeach.frame.dao;

class DaoTable extends DaoObject{
	private static final long serialVersionUID = 7119551887454575960L;
	private String table;
	private boolean update;
	private boolean insert;
	private boolean delete;
	private String[] primarys;

	String getTable() {
		return table;
	}

	void setTable(String table) {
		this.table = table;
	}

	boolean isUpdate() {
		return update;
	}

	void setUpdate(boolean update) {
		this.update = update;
	}

	boolean isInsert() {
		return insert;
	}

	void setInsert(boolean insert) {
		this.insert = insert;
	}

	boolean isDelete() {
		return delete;
	}

	void setDelete(boolean delete) {
		this.delete = delete;
	}

	String[] getPrimarys() {
		return primarys;
	}

	void setPrimarys(String[] primarys) {
		this.primarys = primarys;
	}

}
