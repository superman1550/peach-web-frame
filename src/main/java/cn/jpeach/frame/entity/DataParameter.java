package cn.jpeach.frame.entity;

public class DataParameter {
	private String key;
	private Integer page;
	private Integer rows;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("key=" + key);
		buffer.append("rows=" + rows);
		buffer.append("page=" + page);
		return buffer.toString();
	}
}
