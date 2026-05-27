package cn.jpeach.frame.dao;

import java.sql.SQLException;

import cn.jpeach.frame.exception.FrameException;

public class DaoTransaction {
	public static final String SUCCESS = "1";

	public String doExecute(DaoTransactionExecute execute) throws SQLException {
		DataSource source = new DaoDataSource();
		try {
			source.getConnection().setAutoCommit(false);
			execute.doInTransaction(source);
			source.getConnection().commit();
			return SUCCESS;
		} catch (DaoTransactionException e) {
			source.getConnection().rollback();
			return e.getMessage();
		} catch (SQLException e) {
			source.getConnection().rollback();
			throw new FrameException("数据提交异常：", e);
		} finally {
			source.getConnection().close();
		}
	}

	protected void throwException(String msg) {
		throw new DaoTransactionException(msg);
	}
}
