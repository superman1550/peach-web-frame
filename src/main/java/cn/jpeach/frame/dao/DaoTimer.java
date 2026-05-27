package cn.jpeach.frame.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.text.StringSubstitutor;

import cn.jpeach.frame.core.FrameInstance;
import cn.jpeach.frame.util.ArrayUtil;

class DaoTimer {
	private static final String QUERY_SQL_INFO = "name=${daoName}|sql=${sql}|parameter={${parameter}}|count=${count}|time=${time}";
	private static final String COMMIT_SQL_INFO = "name=${daoName}|sql=${sql}|parameter={${parameter}}";
	private long timestamp;
	private DaoModel model;

	public DaoTimer() {
		super();
	}

	public DaoTimer(DaoModel model) {
		super();
		this.model = model;
	}

	void reset() {
		this.timestamp = System.currentTimeMillis();
	}

	void reload(Object[] parameters, int count) {
		long ts = System.currentTimeMillis();
		Map<String, Object> valuesMap = new HashMap<String, Object>();
		valuesMap.put("daoName", model.getName());
		valuesMap.put("sql", model.getSql());
		valuesMap.put("time", (ts - this.timestamp) + "ms");
		valuesMap.put("count", count);
		String parameter = String.join(",", ArrayUtil.toString(parameters == null ? new Object[] {} : parameters));
		valuesMap.put("parameter", parameter);
		valuesMap.put("timestamp", System.currentTimeMillis());
		StringSubstitutor sub = new StringSubstitutor(valuesMap);
		FrameInstance.getContext().printInfo((sub.replace(QUERY_SQL_INFO)));
	}

	void printSql(String sql, List<String> parms) {
		Map<String, Object> valuesMap = new HashMap<String, Object>();
		valuesMap.put("daoName", model.getName());
		valuesMap.put("sql", sql);
		valuesMap.put("parameter", String.join(",", parms));
		StringSubstitutor sub = new StringSubstitutor(valuesMap);
		FrameInstance.getContext().printInfo(sub.replace(COMMIT_SQL_INFO));
	}

	void printError(String msg, Throwable throwable) {
		FrameInstance.getContext().printError(msg, throwable);
	}

	void beginTransaction() {
		FrameInstance.getContext().printInfo("beginTransaction");
	}

	void commitTransaction() {
		FrameInstance.getContext().printInfo("commitTransaction");
	}

	void rollbackTransaction() {
		FrameInstance.getContext().printInfo("rollbackTransaction");
	}
}
