package cn.jpeach.frame.dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface DataSource {
	public Connection getConnection() throws SQLException;

	public boolean isClose() throws SQLException;
}
