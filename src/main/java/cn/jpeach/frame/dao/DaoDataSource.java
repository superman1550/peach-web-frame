package cn.jpeach.frame.dao;

import java.sql.Connection;
import java.sql.SQLException;

import cn.jpeach.frame.core.FrameDataSource;

class DaoDataSource implements DataSource {
	private Connection connection;

	@Override
	public Connection getConnection() throws SQLException {
		if (connection == null) {
			return connection = FrameDataSource.getConnect();
		} else {
			return connection;
		}
	}

	@Override
	public boolean isClose() throws SQLException {
		return connection.isClosed();
	}
}
