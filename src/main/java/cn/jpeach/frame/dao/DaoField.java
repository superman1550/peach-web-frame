package cn.jpeach.frame.dao;

class DaoField extends DaoObject{
	private static final long serialVersionUID = 5160994155874273933L;
	private String name;
	private int type;
	private String[] table;

	String getName() {
		return name;
	}

	void setName(String name) {
		this.name = name;
	}

	int getType() {
		return type;
	}

	void setType(int type) {
		this.type = type;
	}

	String[] getTable() {
		return table;
	}

	void setTable(String[] table) {
		this.table = table;
	}

}
