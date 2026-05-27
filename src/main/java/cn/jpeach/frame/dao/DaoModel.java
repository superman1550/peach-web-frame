package cn.jpeach.frame.dao;

class DaoModel extends DaoObject{
	private static final long serialVersionUID = 2224066124884824672L;
	private String name;
	private String sql;
	private DaoTable[] tables = new DaoTable[] {};
	private DaoField[] fields = new DaoField[] {};
	private int[] parameters = new int[] {};

	DaoModel() {
		super();
	}

	DaoModel(String sql) {
		super();
		this.sql = sql;
	}

	String getName() {
		return name;
	}

	void setName(String name) {
		this.name = name;
	}

	String getSql() {
		return sql;
	}

	void setSql(String sql) {
		this.sql = sql;
	}

	int[] getParameters() {
		return parameters;
	}

	void setParameters(int[] parameters) {
		this.parameters = parameters;
	}

	DaoTable[] getTables() {
		return tables;
	}

	void setTables(DaoTable[] tables) {
		this.tables = tables;
	}

	DaoField[] getFields() {
		return fields;
	}

	void setFields(DaoField[] fields) {
		this.fields = fields;
	}

}
