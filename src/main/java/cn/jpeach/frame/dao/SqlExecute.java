package cn.jpeach.frame.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import cn.jpeach.frame.core.FrameDataSource;
import cn.jpeach.frame.exception.FrameException;

public final class SqlExecute {
	private Connection connection;
	private String sql;
	private String table;
	private Integer[] paramterTypes;
	private Object[] paramters;

	public SqlExecute(String sql, String table) {
		this(null, sql, table);
	}

	public SqlExecute(Connection connection, String sql, String table) {
		this.connection = connection;
		this.sql = sql;
		this.table = table;
		this.paramterTypes = new Integer[] {};
		this.paramters = new Object[] {};
	}

	public int update() {
		try {
			Connection curr = this.connection;
			if (curr == null) {
				curr = FrameDataSource.getConnect();
			}
			try (PreparedStatement statement = curr.prepareStatement(sql)) {
				for (int i = 0; i < paramterTypes.length; i++) {
					statement.setObject(i + 1, paramters[i], paramterTypes[i]);
				}
				int res = statement.executeUpdate();
				System.out.println(table);
				return res;
			}
		} catch (SQLException e) {
			throw new FrameException("数据库异常", e);
		}
	}

	public int update(Object[] paramters) {
		this.paramters = paramters;
		return this.update();
	}
}
