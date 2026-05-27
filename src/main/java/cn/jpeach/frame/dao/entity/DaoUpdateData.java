package cn.jpeach.frame.dao.entity;

import java.util.List;
import java.util.Map;

public class DaoUpdateData {
	private List<Map<String, Object>> inserted;
	private List<Map<String, Object>> deleted;
	private List<Map<String, Object>> updated;

	public List<Map<String, Object>> getInserted() {
		return inserted;
	}

	public void setInserted(List<Map<String,Object>> inserted) {
		this.inserted = inserted;
	}

	public List<Map<String, Object>> getDeleted() {
		return deleted;
	}

	public void setDeleted(List<Map<String, Object>> deleted) {
		this.deleted = deleted;
	}

	public List<Map<String, Object>> getUpdated() {
		return updated;
	}

	public void setUpdated(List<Map<String, Object>> updated) {
		this.updated = updated;
	}

}
